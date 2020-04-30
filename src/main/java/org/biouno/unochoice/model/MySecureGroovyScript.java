package org.biouno.unochoice.model;

import java.io.Serializable;
import java.util.List;

import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.NonNull;
import groovy.lang.Binding;
import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;
import hudson.model.TaskListener;
import org.jenkinsci.Symbol;
import org.jenkinsci.plugins.scriptsecurity.sandbox.groovy.SecureGroovyScript;
import org.jenkinsci.plugins.scriptsecurity.scripts.ClasspathEntry;
import org.kohsuke.stapler.DataBoundConstructor;

public class MySecureGroovyScript extends AbstractDescribableImpl<MySecureGroovyScript> implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -1791627302909128672L;
  
  private SecureGroovyScript secureScript;

  @DataBoundConstructor
  public MySecureGroovyScript(@NonNull String script, boolean sandbox, @CheckForNull List<ClasspathEntry> classpath) {
    this.secureScript = new SecureGroovyScript(script, sandbox, classpath);
  }

  public MySecureGroovyScript configuringWithNonKeyItem() {
    this.secureScript.configuringWithNonKeyItem();
    return this;
  }

  public Object evaluate(
    ClassLoader loader, Binding binding,
    @javax.annotation.CheckForNull TaskListener listener) throws Exception {
    return this.secureScript.evaluate(loader, binding, listener);
  }

  public boolean isSandbox() {
    return this.secureScript.isSandbox();
  }

  public String getScript() {
    return this.secureScript.getScript();
  }

  public List<ClasspathEntry> getClasspath() {
    return this.secureScript.getClasspath();
  }

  @Symbol("activeSecureGroovy")
  @Extension
  public static final class DescriptorImpl extends Descriptor<MySecureGroovyScript> {
    @Override
    public String getDisplayName() {
      return "Groovy Script"; // not intended to be displayed on its own
    }
  }

}
