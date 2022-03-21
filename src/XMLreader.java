import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;
import javax.xml.validation.*;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;

/**
 * We will use very basic restrictions on the xml:
 * 
??? -> lower case letters and underscores
*** -> alphanumeric and symbols not including <> /
 *
 * @author TuckerKA20
 * @version 1/15/19
 */
public class XMLreader
{
	public static void main( String[] args )
	{
		Scanner scan = null;
		
		try
		{
			String contents = getFileContents( "./src/strings.xml" );
			scan = new Scanner( contents );
			System.out.print( verifier( scan ) );
		}
		catch ( Exception e )
		{
			System.out.print( e );
		}
		finally
		{
			if ( scan != null )
			{
				scan.close();
			}
		}
		
	}
	
	private static String getFileContents( String filename ) throws IOException
	{
		return new String( Files.readAllBytes( Paths.get( filename ) ) );
	}
	
	/*
	private static void writeFileContents( String filename, String data ) throws IOException
	{
		FileWriter f = new FileWriter( new File( filename ) );
		f.write( data );
		f.close();
	}
	*/
	
	public static boolean verifier( Scanner scan )
	{
		//create a regex for every line
		Pattern p = Pattern.compile( "<\\?xml version=\\\"1\\.0\\\" encoding=\\\"UTF-8\\\"\\?>" );
		
		while ( scan.hasNext() )		{
			//get every line and compare with Matcher class
			String current = scan.nextLine();
			Matcher m = p.matcher( current );
			if ( m.matches() != true )
			{
				return false;
			}
		}
		return true;
	}

}
