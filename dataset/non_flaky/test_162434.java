class DummyClass_162434 {
    @Test
    public void extraHostTest() throws IOException {
        BufferedReader br = getReaderForContainerPort80(alpineExtrahost);

        // read hosts file from container
        StringBuffer hosts = new StringBuffer();
        String line = br.readLine();
        while (line != null) {
            hosts.append(line);
            hosts.append("\n");
            line = br.readLine();
        }

        Matcher matcher = Pattern.compile("^192.168.1.10\\s.*somehost", Pattern.MULTILINE).matcher(hosts.toString());
        assertTrue("The hosts file of container contains extra host", matcher.find());
    }

}