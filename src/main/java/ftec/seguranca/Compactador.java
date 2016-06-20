/*
 * File:   Compactador.java
 *
 * Created on 14/06/16, 13:30
 */
package ftec.seguranca;

import java.nio.ByteBuffer;

/**
 * @author higor
 */
public class Compactador
{
  private String _input;
  private String _output;

  public Compactador setInput(String input)
  {
    _input = input;
    return this;
  }

  private String compactarRecur(ByteBuffer in)
  {
    String output = "";
    ByteBuffer out = ByteBuffer.allocate(in.limit());
    byte anterior = 0;
    int remaining = in.remaining();
    for (int i = 0; i < remaining; i++)
    {
      in.mark();
      byte b = in.get();
      if (i == 0)
      {
        anterior = b;
        out.put(anterior);
        continue;
      }
      if (b != anterior && i > 3)
      {
        out.clear();
        out.put(Integer.valueOf(i).toString().getBytes()[0]);
        out.put(anterior);
        break;
      }
      if (b != anterior && i < 4)
      {
        break;
      }

      anterior = b;
      out.put(anterior);
    }

    if (in.remaining() > 0)
    {
      out.flip();
      byte[] bs = new byte[out.limit()];
      out.get(bs);
      output = new String(bs);
      in.reset();
      output += compactarRecur(in);
    }
    else
    {
      out.flip();
      if (out.limit() > 3)
      {
        return String.format("%d%c", out.limit(), out.get());
      }
      byte[] bs = new byte[out.limit()];
      out.get(bs);
      output = new String(bs);
    }

    return output;
  }

  private String compactar(ByteBuffer in)
  {
    String output;
    ByteBuffer out = ByteBuffer.allocate(in.limit());
    ByteBuffer tmp = ByteBuffer.allocate(in.limit());
    byte anterior = 0;
    int remaining = in.remaining();
    for (int i = 0; i < remaining; i++)
    {
      byte b = in.get();
      if (i == 0)
      {
        anterior = b;
        tmp.put(anterior);
        continue;
      }
      if (b != anterior && tmp.position() > 3)
      {
        tmp.flip();
        out.put(Integer.valueOf(tmp.limit()).toString().getBytes());
        out.put(anterior);
        tmp.clear();
      }
      else if (b != anterior && tmp.position() < 4)
      {
        tmp.flip();
        out.put(tmp);
        tmp.clear();
      }

      anterior = b;
      tmp.put(anterior);
    }

    output = finaliza(out, tmp);

    return output;
  }

  private String finaliza(ByteBuffer out, ByteBuffer tmp)
  {
    String output;
    tmp.flip();
    if (tmp.limit() > 3)
    {
      out.put(Integer.valueOf(tmp.limit()).toString().getBytes());
      out.put(tmp.get());
    }
    else
      out.put(tmp);

    out.flip();
    byte[] bs = new byte[out.limit()];
    out.get(bs);
    output = new String(bs);
    return output;
  }

  public void compactar()
  {
    ByteBuffer in = ByteBuffer.allocate(_input.length());
    in.put(_input.getBytes());
    in.flip();
    _output = compactar(in);
  }

  public String getOutput()
  {
    return _output;
  }
}
