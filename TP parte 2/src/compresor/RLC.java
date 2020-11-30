package compresor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class RLC implements ICompresor
{
	public void comprimir(String direccionOrigen, String direccionDestino, String nombre)
			throws IOException, FileNotFoundException
	{

		BufferedReader reader = null;
		BufferedWriter writer = null;
		try
		{
			writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(direccionDestino + nombre + ".rlc"), "UTF-8"));
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(direccionOrigen), "UTF-8"));
			int caracterAnterior = reader.read();
			int caracter;

			int acumulador = 1;

			while (caracterAnterior != -1)
			{
				caracter = reader.read();
				if (caracter == caracterAnterior)
				{
					acumulador++;
				} else
				{
					if (acumulador > 1)
					{
						writer.write((char) caracterAnterior + Integer.toString(acumulador) + " ");
					} else
					{
						writer.write((char) caracterAnterior + " ");
					}
					caracterAnterior = caracter;
					acumulador = 1;
				}

			}

		} finally
		{
			if (reader != null)
			{

				reader.close();

			}
			if (writer != null)
			{

				writer.close();

			}
		}

	}

	public void descomprimir(String direccionOrigen, String direccionDestino, String nombre)
			throws IOException, FileNotFoundException
	{
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try
		{
			writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(direccionDestino + nombre + ".txt"), "UTF-8"));
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(direccionOrigen), "UTF-8"));

			int charCantidad;
			int caracter = reader.read();
			while (caracter != -1)
			{
				StringBuilder sb = new StringBuilder();
				charCantidad = reader.read();
				while (charCantidad != -1 && charCantidad != ' ')
				{
					sb.append((char) charCantidad);
					charCantidad = reader.read();
				}
				if (sb.toString().isEmpty())
				{
					writer.write((char) caracter);
				} else
				{
					for (int i = 0; i < Integer.parseInt(sb.toString()); i++)
					{
						writer.write((char) caracter);
					}
				}
				if (charCantidad != -1)
				{
					caracter = reader.read();
				}
			}

		} finally
		{
			if (reader != null)
			{
				reader.close();

			}
			if (writer != null)
			{

				writer.close();

			}
		}
	}

	@Override
	public double getTasaCompresion()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRendimiento()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRedundancia()
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
