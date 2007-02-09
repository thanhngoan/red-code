
public class RedUtil {
	
	/**
	 * CITATION: from http://www.javaworld.com/javaworld/jw-08-1999/jw-08-cooltools.html?page=2
	 * @param token
	 * @param strings
	 * @return
	 */
    public static String join( String token, String[] strings )
    {
        StringBuffer sb = new StringBuffer();
        
        for( int x = 0; x < ( strings.length - 1 ); x++ )
        {
            sb.append( strings[x] );
            sb.append( token );
        }
        sb.append( strings[ strings.length - 1 ] );
        
        return( sb.toString() );
    }

}
