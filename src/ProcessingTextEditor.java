import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.rsyntaxtextarea.CodeTemplateManager;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.templates.CodeTemplate;
import org.fife.ui.rsyntaxtextarea.templates.StaticCodeTemplate;
import org.fife.ui.rtextarea.RTextScrollPane;

/**
 * A basic text editor for processing that uses RSyntaxTextArea. Supports basic
 * auto bracket completion, code folding, undo/redo, code templates and
 * autocomplete.
 * <p>
 * 
 * This example uses RSyntaxTextArea 2.0.1, and has been adapted from the
 * examples provided on the websites of <a href =
 * "http://fifesoft.com/rsyntaxtextarea">RSyntaxTextArea</a> and <a href =
 * "http://fifesoft.com/autocomplete">AutoComplete</a>.
 */
public class ProcessingTextEditor extends JFrame {

  private static final long serialVersionUID = -3355581244887481173L;

  public ProcessingTextEditor() {

    JPanel cp = new JPanel(new BorderLayout());

    RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);
    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
    textArea.setCodeFoldingEnabled(true);
    textArea.setAntiAliasingEnabled(true);
    RTextScrollPane sp = new RTextScrollPane(textArea);
    sp.setFoldIndicatorEnabled(true);
    cp.add(sp);

    setupCodeTemplate();

    setupAutoComplete(textArea);

    setContentPane(cp);
    setTitle("Text Editor Demo");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);

  }

  private void setupAutoComplete(RSyntaxTextArea textArea) {
    CompletionProvider provider = createCompletionProvider();
    AutoCompletion ac = new AutoCompletion(provider);
    ac.install(textArea);
  }

  private CompletionProvider createCompletionProvider() {

    DefaultCompletionProvider provider = new DefaultCompletionProvider();

    provider.addCompletion(new BasicCompletion(provider, "abstract"));
    provider.addCompletion(new BasicCompletion(provider, "assert"));
    provider.addCompletion(new BasicCompletion(provider, "break"));
    provider.addCompletion(new BasicCompletion(provider, "case"));
    // ... etc ...
    provider.addCompletion(new BasicCompletion(provider, "transient"));
    provider.addCompletion(new BasicCompletion(provider, "try"));
    provider.addCompletion(new BasicCompletion(provider, "void"));
    provider.addCompletion(new BasicCompletion(provider, "volatile"));
    provider.addCompletion(new BasicCompletion(provider, "while"));
    provider.addCompletion(new BasicCompletion(provider, "sin"));
    provider.addCompletion(new BasicCompletion(provider, "size"));
    provider.addCompletion(new BasicCompletion(provider, "rect"));
    provider.addCompletion(new BasicCompletion(provider, "rectMode"));

    return provider;

  }

  private void setupCodeTemplate() {
    RSyntaxTextArea.setTemplatesEnabled(true);
    CodeTemplateManager ctm = RSyntaxTextArea.getCodeTemplateManager();

    // Code template for sysout
    CodeTemplate ct = new StaticCodeTemplate("sysout", "System.out.println(",
        null);
    ctm.addTemplate(ct);
    // Code template for for loop
    ct = new StaticCodeTemplate("floop", "for (int i=0; i<",
        "; i++) {\n  \n}\n");
    ctm.addTemplate(ct);
    // Code template for void draw()
    ct = new StaticCodeTemplate("draw", "void draw() {\n  ", "\n}");
    ctm.addTemplate(ct);
    // Code template for void setup()
    ct = new StaticCodeTemplate("setup", "void setup() {\n  ", "\n}");
    ctm.addTemplate(ct);
  }

  public static void main(String[] args) {
    // Start all Swing applications on the EDT.
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new ProcessingTextEditor().setVisible(true);
      }
    });
  }
}
