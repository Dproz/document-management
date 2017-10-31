/**
 * 
 */
package com.ceitechs.dproz.shared.rest.util;

/**
 * @author vowino
 *
 */
public class TokenUtils {
	
	public static String getUserNameFromToken(String authToken) {
        if (null == authToken) {
            return null;
        }
        // TODO: need implementation on how to split the authToken

        //String[] parts = authToken.split(":");
        return new String("vctrowino@yahoo.com"); 
}

}
