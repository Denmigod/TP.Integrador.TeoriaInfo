package archivos;

import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStreamReader;

public abstract class LeeArchivo
{

	public static String leerArchivo(String direccion)
	{
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(direccion), "UTF-8"));
			int caracter = reader.read();
			while (caracter != -1)
			{
				sb.append((char) caracter);
				caracter = reader.read();
			}

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (reader != null)
			{
				try
				{
					reader.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
}
