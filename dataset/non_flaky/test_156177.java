class DummyClass_156177 {
  @Test
  public void runTestAndCompareOutput() throws IOException {
    runSoot();
    String comparisonOutput = createComparison();

    /*
     * Print output for comparison to file for debugging purposes.
     */
    File compareFile = new File("sootOutput/" + getTargetClass() + ".asm.compare");
    PrintWriter ow = new PrintWriter(compareFile);
    ow.print(comparisonOutput);
    ow.flush();
    ow.close();

    File targetFile = new File("sootOutput/" + getTargetClass() + ".asm");
    assertTrue(String.format("Soot output file %s not found", targetFile.getAbsolutePath()), targetFile.exists());
    Scanner sootOutput = new Scanner(targetFile);
    Scanner compareOutput = new Scanner(comparisonOutput);

    try {
      System.out.println(
          String.format("Comparing files %s and %s...", compareFile.getAbsolutePath(), targetFile.getAbsolutePath()));
      int line = 1;
      while (compareOutput.hasNextLine()) {
        // Soot-output must have as much lines as the compared output.
        assertTrue(String.format("Too few lines in Soot-output for class %s! Current line: %d. Comparison output: %s",
            getTargetClass(), line, comparisonOutput), sootOutput.hasNextLine());

        // Get both lines
        String compare = compareOutput.nextLine();

        String output = sootOutput.nextLine();

        // Compare lines
        assertTrue(String.format("Expected line %s, but got %s in line %d for class %s", compare.trim(), output.trim(), line,
            getTargetClass()), compare.equals(output));
        ++line;
      }

      assertFalse(String.format("Too many lines in Soot-output for class %s!", getTargetClass()), sootOutput.hasNextLine());
      System.out.println("File comparison successful.");
    } finally {
      sootOutput.close();
      compareOutput.close();
    }
  }

}