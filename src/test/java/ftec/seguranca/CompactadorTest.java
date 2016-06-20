package ftec.seguranca;

import org.junit.Assert;
import org.junit.Test;

/*
 * File:   CompactadorTest.java
 *
 * Created on 14/06/16, 13:29
 */
public class CompactadorTest
{
  @Test
  public void compactaUmaPalavra()
  {
    String entrada = "AAAAAAA";
    String esperado = "7A";
    Compactador compactador = new Compactador();
    compactador.setInput(entrada).compactar();
    String saida = compactador.getOutput();
    Assert.assertEquals(esperado, saida);
  }

  @Test
  public void compactaDuasPalavrasSeparadasPorEspaco()
  {
    String entrada = "AAAAAAA BBBBBBBBB";
    String esperado = "7A 9B";
    Compactador compactador = new Compactador();
    compactador.setInput(entrada).compactar();
    String saida = compactador.getOutput();
    Assert.assertEquals(esperado, saida);
  }

  @Test
  public void compactaDuasPalavrasJuntas()
  {
    String entrada = "AAAAAAABBBBBBBBB";
    String esperado = "7A9B";
    Compactador compactador = new Compactador();
    compactador.setInput(entrada).compactar();
    String saida = compactador.getOutput();
    Assert.assertEquals(esperado, saida);
  }

  @Test
  public void compactaDuasPalavrasJuntasEUmaSeparada()
  {
    String entrada = "AAAAAAABBBBBBBBB CCCCCCCCCCCCCC";
    String esperado = "7A9B 14C";
    Compactador compactador = new Compactador();
    compactador.setInput(entrada).compactar();
    String saida = compactador.getOutput();
    Assert.assertEquals(esperado, saida);
  }

  @Test
  public void compactaUmaPalavrasCom3Char()
  {
    String entrada = "AAA";
    String esperado = "AAA";
    Compactador compactador = new Compactador();
    compactador.setInput(entrada).compactar();
    String saida = compactador.getOutput();
    Assert.assertEquals(esperado, saida);
  }
}