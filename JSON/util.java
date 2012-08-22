package com.rahul.cheerfoolz.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONStringer;


public class util {

	
	public static DefaultHttpClient httpClient;
	
	static DefaultHttpClient client = new DefaultHttpClient();
	
	/**
	 * 
	 * @param url
	 * @param username
	 * @param password
	 * @return
	 * 
	 *  For register
	 */
	public static String makeWebCall1(String url, String uname,String mail, String name, String address, String occupation, Date dateofbirth)
	{
		//DefaultHttpClient client = new DefaultHttpClient();
		
		DateFormat dateFormatISO8601 = new SimpleDateFormat("yyyy-MM-dd");
		String strDob = dateFormatISO8601.format(dateofbirth);
		
		HttpPost post = new HttpPost(url);
	
		 List<NameValuePair> params = new ArrayList<NameValuePair>();
		
	        params.add(new BasicNameValuePair("name",uname));
	        params.add(new BasicNameValuePair("mail", mail));
	        params.add(new BasicNameValuePair("profile_name",name));
	        params.add(new BasicNameValuePair("profile_address", address));
	        params.add(new BasicNameValuePair("profile_occupation", occupation));
	        params.add(new BasicNameValuePair("profile_dob", strDob));
	       	      
        UrlEncodedFormEntity formEntity = null;
		try {
				formEntity = new UrlEncodedFormEntity(params);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        post.setEntity(formEntity);
        
		try {
			//post.setEntity(new StringEntity(requestString));
			
			HttpResponse response = client.execute(post);
		
			int statusCode = response.getStatusLine().getStatusCode();
			System.out.println("=========> statusCode register=====> "+statusCode);	
			if (statusCode == HttpStatus.SC_OK)
			{
				HttpEntity entity = response.getEntity();
				InputStream is = entity.getContent();
				return iStream_to_String(is);
			}
			else
			{
				return "Hello This is status ==> :"+String.valueOf(statusCode);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 *  Makewebcall For login
	 * @param url
	 * @param uname
	 * @param pass
	 * @return
	 */
	public static String makeWebCall(String url, String uname, String pass) {
		//DefaultHttpClient client = new DefaultHttpClient();

		HttpPost post = new HttpPost(url);

		List<NameValuePair> params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("username", uname));
		params.add(new BasicNameValuePair("password", pass));

		UrlEncodedFormEntity formEntity = null;
		try {
			formEntity = new UrlEncodedFormEntity(params);
			
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		post.setEntity(formEntity);
		
		try {
			// post.setEntity(new StringEntity(requestString));

			HttpResponse response = client.execute(post);
			System.out.println("=========> Responsehello => " + response);
			int statusCode = response.getStatusLine().getStatusCode();
			System.out.println("=========> statusCode login => " + statusCode);
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				InputStream is = entity.getContent();
				return iStream_to_String(is);
			} else {
				return "Hello This is status ==> :"
						+ String.valueOf(statusCode);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
	
	 /**
     *   For the Post Idea Page
     *   
     * @param url
     * @param title
     * @param spinnercategoryIndexId
     * @param spinnerFounderIndexId
     * @param story_bhnd_idea
     * @param idea_dtls
     * @param spinnerBudgetId
     * @return
	 * @throws JSONException 
     */
    
	  
	
	 // for the json value
	 
	  public static String makeWebForPostIdea(String url, String title,JSONObject jSONCategory, JSONArray jsonArrayMyFounder, JSONArray jsonArrayStoryBhndIdea, JSONArray jsonArrayIdeaDetails,JSONArray jsonArrayBudget) throws JSONException
	    {
	            //DefaultHttpClient client = new DefaultHttpClient();
	            	            
	            HttpPost post = new HttpPost(url);
	            List<NameValuePair> params = new ArrayList<NameValuePair>();
	           
	            System.out.println("############## value of the json before pass on the server =====>  "+jSONCategory);
	            
	            JSONStringer jsonstringercategory = new JSONStringer();
	            //JSONStringer jsonstringerFounder = new JSONStringer();
	            JSONStringer jsonstringerstoryBhndIdea = new JSONStringer();
	            JSONStringer jsonstringerIdeaDetails = new JSONStringer();
	            JSONStringer jsonstringerCategory = new JSONStringer();
	            
	            // For the category covert the json to the stringer
	            if (jSONCategory!=null) 
	            {
	            	
	                Iterator<String> itKeys = jSONCategory.keys();
	                if(itKeys.hasNext())
	                	jsonstringercategory.object();
	                while (itKeys.hasNext()) 
	                {
	                    String k=itKeys.next();
	                    jsonstringercategory.key(k).value(jSONCategory.get(k));
	                    Log.e("keys "+k,"value "+jSONCategory.get(k).toString());
	                }             
	            }
	            jsonstringercategory.endObject();
	            
	            // For the Founder covert the json to the stringer
	            System.out.println("############## value of the json array for founder =====>  "+jsonArrayMyFounder);
	            
	            JSONStringer jsonstringerFounder = new JSONStringer().array().object().key("nid").value("111").endObject().endArray();
	            
	            System.out.println("############## value of the json string builder for founder =====>  "+jsonstringerFounder);
	            if (jsonArrayMyFounder!=null) 
	            {
	            	
	                Iterator<String> itKeys = jsonArrayMyFounder.keys();
	                if(itKeys.hasNext())
	                	jsonstringerFounder.object();
	                while (itKeys.hasNext()) 
	                {
	                    String k=itKeys.next();
	                    jsonstringerFounder.key(k).value(jsonArrayMyFounder.get(k));
	                    Log.e("keys "+k,"value "+jsonArrayMyFounder.get(k).toString());
	                }             
	            }
	            jsonstringerFounder.endObject();
	            
	            System.out.println("############## value of the json after pass on the server =====>  "+jsonstringerFounder.toString());
	            
	            params.add(new BasicNameValuePair("title",title));
	            params.add(new BasicNameValuePair("taxonomy",jsonstringercategory.toString()));
	           // params.add(new BasicNameValuePair("taxonomy":{Integer.toString(category):{"tid":Integer.toString(category)}});
	            params.add(new BasicNameValuePair("field_founder_profile_refrence",jsonArrayMyFounder);
	            params.add(new BasicNameValuePair("field_story_idea", jsonArrayStoryBhndIdea));
	            params.add(new BasicNameValuePair("field_idea_details",jsonArrayIdeaDetails));
	            params.add(new BasicNameValuePair("field_idea_budget", jsonArrayBudget);
	            
	                         
	           UrlEncodedFormEntity formEntity = null;
	            try {
	                            formEntity = new UrlEncodedFormEntity(params);
	            } catch (UnsupportedEncodingException e1) {
	                    // TODO Auto-generated catch block
	                    e1.printStackTrace();
	            }
	    
	    post.setEntity(formEntity);
	    
	            try {
	                    //post.setEntity(new StringEntity(requestString));
	                    
	                    HttpResponse response = client.execute(post);
	            
	                    int statusCode = response.getStatusLine().getStatusCode();
	                    System.out.println("=========> statusCode =====> "+statusCode);        
	                    if (statusCode == HttpStatus.SC_OK)
	                    {
	                            HttpEntity entity = response.getEntity();
	                            InputStream is = entity.getContent();
	                            return iStream_to_String(is);
	                    }
	                    else
	                    {
	                            return "Hello This is status ==> :"+String.valueOf(statusCode);
	                    }
	            } catch (UnsupportedEncodingException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	            } catch (ClientProtocolException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	            } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	            }
	            
	            return null;
	    }
	 
	  
			
	/**
	 * 
	 * @param url
	 * @param requestString - is the JSON Request string
	 * @return String
	 */
	public static String makeWebCall(String url, int requestString)
	{
		//DefaultHttpClient client = new DefaultHttpClient();
		
		HttpPost post = new HttpPost(url);
		
		try {
			post.setHeader("Content-Type", "application/x-www-form-urlencoded ; charset=utf-8");
			
			post.setEntity(new StringEntity(requestString));
			
			HttpResponse response = client.execute(post);
			
			int statusCode = response.getStatusLine().getStatusCode();
			
			if (statusCode == HttpStatus.SC_OK)
			{
				HttpEntity entity = response.getEntity();
				InputStream is = entity.getContent();
				return iStream_to_String(is);
			}
			else
			{
				return "Hello "+String.valueOf(statusCode);
			}
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}


	 /**
	  *  For the Logout Funcation
	  * @param url
	  * @param sessionId
	  * @return
	  */
	 
	 public static String makeWebCallLogout(String url, int sessionId) {
			//DefaultHttpClient client = new DefaultHttpClient();

			HttpPost post = new HttpPost(url);

			List<NameValuePair> params = new ArrayList<NameValuePair>();

			params.add(new BasicNameValuePair("username",Integer.toString(sessionId)));
			
			UrlEncodedFormEntity formEntity = null;
			try {
				formEntity = new UrlEncodedFormEntity(params);
				
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			post.setEntity(formEntity);
			
			try {
				// post.setEntity(new StringEntity(requestString));

				HttpResponse response = client.execute(post);
				System.out.println("=========> Responsehello => " + response);
				int statusCode = response.getStatusLine().getStatusCode();
				System.out.println("=========> statusCode logout=> " + statusCode);
				if (statusCode == HttpStatus.SC_OK) {
					HttpEntity entity = response.getEntity();
					InputStream is = entity.getContent();
					return iStream_to_String(is);
				} else {
					return "Hello This is status ==> :"
							+ String.valueOf(statusCode);
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;

		}
	 
	 
	/**
	 *  For activities
	 * @param url
	 * @return
	 */
	
	public static String makeWebCall(String url) {

       // DefaultHttpClient client = new DefaultHttpClient();

        HttpGet httpRequest = new HttpGet(url);
      //  HttpPost post = new HttpPost(url);

        try {

        	HttpResponse httpResponse = client.execute(httpRequest);
           
        	System.out.println("=========> Response from login123 => "+httpResponse);
        	
        	 final int statusCode = httpResponse.getStatusLine().getStatusCode();
        	 System.out.println("=========> statusCode from the url => " + statusCode);
             if (statusCode != HttpStatus.SC_OK) {
              /*  Log.i(getClass().getSimpleName(),
                    "Error => " + statusCode + " => for URL " + url);*/
                return null;
             }
              
             HttpEntity entity = httpResponse.getEntity();
            // String html = EntityUtils.toString(entity);
             InputStream instream = null;

             if (entity != null) {
                  instream = entity.getContent();
                  //instream.close();
             }
             
				return iStream_to_String(instream);
        }
        catch (IOException e) {
        	httpRequest.abort();
          // Log.w(getClass().getSimpleName(), "Error for URL =>" + url, e);
        }

        return null;

     }
	
	public static String iStream_to_String(InputStream is1) {
		BufferedReader rd = new BufferedReader(new InputStreamReader(is1), 4096);
		String line;
		StringBuilder sb = new StringBuilder();
		try {
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String contentOfMyInputStream = sb.toString();
		return contentOfMyInputStream;
	}

}
