package Pages;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OTPClass {

	private static final String API_TOKEN = "7d51e00aa4de43ebb373c10b50d33994";
    private static final String DOMAIN = "private";
    private static final OkHttpClient client = new OkHttpClient();

    //it will wait 20 seconds for getting the latest OTP
    public static String fetchLatestOTP() throws Exception {
        String inboxName = "Dr58401";
        long latestSeenTime = 0;
        String otp = null;

        // Step 1: Get current latest message time (before requesting OTP)
        JSONArray initialMessages = getInboxMessages(inboxName);
        if (initialMessages != null && initialMessages.length() > 0) {
            JSONObject latestMsg = initialMessages.getJSONObject(0);
            latestSeenTime = latestMsg.optLong("time", 0);
        }

        System.out.println("Initial message timestamp: " + latestSeenTime);

        int totalWaitTime = 20000;
        int pollInterval = 2000;
        int attempts = totalWaitTime / pollInterval;

        for (int i = 1; i <= attempts; i++) {
            JSONArray messages = getInboxMessages(inboxName);

            if (messages != null && messages.length() > 0) {
                JSONObject newestMsg = messages.getJSONObject(0);
                long msgTime = newestMsg.optLong("time", 0);
                String msgId = newestMsg.getString("id");

                // Step 3: Only process if new message arrived after starting
                if (msgTime > latestSeenTime) {
                    String body = getMessageBody(msgId);

                    if (body != null && body.toLowerCase().contains("otp")) {
                        otp = extractOTP(body);
                        if (otp != null) {
                            System.out.println("Latest OTP fetched: " + otp);
                            return otp;
                        }
                    }
                }
            }

            System.out.println("Waiting for new OTP email... (" + i + ")");
            Thread.sleep(pollInterval);
        }

        throw new Exception("No new OTP email arrived within 20 seconds.");
    }

    // --- Fetch inbox messages ---
    private static JSONArray getInboxMessages(String inboxName) throws IOException {
        String url = "https://api.mailinator.com/api/v2/domains/" + DOMAIN + "/inboxes/" 
                   + inboxName + "?token=" + API_TOKEN;
        Request request = new Request.Builder().url(url).get().build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful() || response.body() == null) return null;

            String resp = response.body().string();
            if (!resp.trim().startsWith("{")) return null;

            JSONObject json = new JSONObject(resp);
            return json.optJSONArray("msgs");
        }
    }

    // --- Fetch message body ---
    private static String getMessageBody(String messageId) throws IOException {
        String url = "https://api.mailinator.com/api/v2/domains/" + DOMAIN + "/messages/" 
                   + messageId + "?token=" + API_TOKEN;
        Request request = new Request.Builder().url(url).get().build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful() || response.body() == null) return null;

            String resp = response.body().string();
            if (!resp.trim().startsWith("{")) return null;

            JSONObject json = new JSONObject(resp);

            if (json.has("body")) return json.getString("body");

            if (json.has("parts")) {
                JSONArray parts = json.getJSONArray("parts");
                for (int i = 0; i < parts.length(); i++) {
                    JSONObject part = parts.getJSONObject(i);
                    if (part.has("body")) return part.getString("body");
                }
            }

            return null;
        }
    }

    // --- Extract OTP ---
    private static String extractOTP(String body) {
        if (body == null || body.isEmpty()) return null;

        // Clean HTML but keep line breaks
        body = body.replaceAll("<br\\s*/?>", "\n")
                   .replaceAll("<[^>]+>", "")
                   .replaceAll("&nbsp;", " ")
                   .trim();

        // Split into lines and find line with OTP
        String[] lines = body.split("\\r?\\n");
        for (String line : lines) {
            if (line.toLowerCase().contains("otp")) {
                Matcher matcher = Pattern.compile("\\b(\\d{6})\\b").matcher(line);
                if (matcher.find()) return matcher.group(1);
            }
        }

        // Fallback: any 6-digit number
        Matcher matcher = Pattern.compile("\\b(\\d{6})\\b").matcher(body);
        return matcher.find() ? matcher.group(1) : null;
    }
}
