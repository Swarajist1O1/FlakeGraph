class DummyClass_159623 {
    @Test
    public void diffToChangeLog() throws Exception{
        if (getDatabase() == null) {
            return;
        }

        runCompleteChangeLog();

        DiffResult diffResult = DiffGeneratorFactory.getInstance().compare(getDatabase(), null, new CompareControl());
        File outputFile = new File("diffToChangeLog_" + getDatabase().getShortName() + ".log");
        if (outputFile.exists())
            outputFile.delete();
        PrintStream writer = new PrintStream(outputFile);

        new DiffToChangeLog(diffResult, new DiffOutputControl(true, true, true, null)).print(writer);
        writer.close();


    }

}