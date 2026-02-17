class DummyClass_156180 {
  @Test
  public void loadClass() {
    G.reset();
    // Location of the rt.jar
    String rtJar = System.getProperty("java.home") + File.separator + "lib" + File.separator + "rt.jar";

    // Run Soot and print output to .asm-files.
    Main.main(new String[] { "-cp", getClassPathFolder() + File.pathSeparator + rtJar, "-process-dir", getTargetFolder(),
        "-src-prec", "only-class", "-output-format", "class", "-asm-backend", "-allow-phantom-refs", "-java-version",
        getRequiredJavaVersion(), getTargetClass() });

    File file = new File("./sootOutput/ConstantPool.class");
    URL[] urls = null;
    try {
      URL url = file.toURI().toURL();
      urls = new URL[] { url };
      URLClassLoader cl = new URLClassLoader(urls);

      cl.loadClass(getTargetClass());

      // cl.close();
      // Java 6 backwards compatibility hack
      try {
        for (Method m : URLClassLoader.class.getDeclaredMethods()) {
          if (m.getName().equals("close")) {
            m.invoke(cl);
            break;
          }
        }
      } catch (Exception e) {
      }
      return;

    } catch (MalformedURLException e) {
      logger.error(e.getMessage(), e);
    } catch (ClassNotFoundException e) {
      logger.error(e.getMessage(), e);
    }

    fail();

  }

}