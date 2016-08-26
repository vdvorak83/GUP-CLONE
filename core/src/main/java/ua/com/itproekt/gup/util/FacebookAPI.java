package ua.com.itproekt.gup.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ua.com.itproekt.gup.exception.FacebookException;
import ua.com.itproekt.gup.model.FacebookProfile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * {@link https://developers.facebook.com/tools/explorer}
 * ******************************************************
 * access_token=EAACEdEose0cBAGnkZAAAHISHXNuzZAO8KRHPmnXX64rTi1v5nv9VkaZAhRFDdxpOsjYk0ZBH3r5IntdgVZAFLl5h6u7nbkyupzvlgpl5IN4r64qEwZChETk7EB3TZCJPlXjt0PkqmZBDPmu17e6nE042d2FmVRZAZCkZCj7njBPMOzbvgZDZD
 */
public class FacebookAPI {

    private String ACCESS_TOKEN;
    public FacebookAPI(String ACCESS_TOKEN){
        this.ACCESS_TOKEN = ACCESS_TOKEN;
    }

	public FBObject_PublicUser get( String UID ) {
		String json = "", jsonPicture = "";
		FBObject_PublicUser result = null;
		try {
            URLConnection fbAPI = new URL( "https://graph.facebook.com/" + UID + "?access_token=" + ACCESS_TOKEN ).openConnection();
            URLConnection fbAPIpicture = new URL( "https://graph.facebook.com/" + UID + "//bgolub?fields=picture&access_token=" + ACCESS_TOKEN ).openConnection();
			HttpURLConnection fbConnection = ( (HttpURLConnection)fbAPI );
            HttpURLConnection fbPictureConnection = ( (HttpURLConnection)fbAPIpicture );

			if( fbConnection.getResponseCode() != 200 && fbPictureConnection.getResponseCode() != 200 ){
				BufferedReader reader = new BufferedReader( new InputStreamReader( fbConnection.getErrorStream() ) );
				String input;
				while( ( input = reader.readLine() ) != null ){
                    json += input;
                }
				reader.close();
				throw new FacebookException( json );
			}

			BufferedReader reader = new BufferedReader( new InputStreamReader( fbAPI.getInputStream() ) );
			String input;
			while( ( input = reader.readLine() ) != null ) {
                json += input;
            }
			reader.close();
            BufferedReader readerPicture = new BufferedReader( new InputStreamReader( fbPictureConnection.getInputStream() ) );
            String inputPicture;
            while( ( inputPicture = readerPicture.readLine() ) != null ) {
                jsonPicture += inputPicture;
            }
            readerPicture.close();

            Gson gson = new Gson();
            result = gson.fromJson( json, FBObject_PublicUser.class );
            JsonParser jsonParser = new JsonParser();
            JsonObject jo = (JsonObject) jsonParser.parse( jsonPicture );
            Map<String,String> image = new HashMap<String, String>();
            image.put("url", jo.get("picture").getAsJsonObject().get("data").getAsJsonObject().get("url").getAsString());
            result.setImage(image);
		} catch ( FacebookException e ){
			e.show();
		} catch ( Exception e ){
			System.err.println( "Facebook API Fatal Error: " + e.getMessage() );
		}
		
		return result;
		
	}

	public class FBObject_PublicUser implements FacebookProfile {
		private String id;
		private String name;
		private String first_name;
		private String last_name;
		private String link;
		private String username;
		private String gender;
		private String locale;
		private String type;
        private Map<String,String> image;
		
		public String getId(){ return id; }
		public String getName(){ return name; }
		public String getFirstName(){ return first_name; }
		public String getLastName(){ return last_name; }
		public String getLink(){ return link; }
		public String getUsername(){ return username; }
		public String getGender(){ return gender; }
		public String getLocale(){ return locale; }
		public String getType(){ return type; }
        public void setImage(Map<String,String> image){ this.image = image; }
        public Map<String,String> getImage(){ return image; }
	}
	
}

