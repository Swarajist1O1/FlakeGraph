class DummyClass_19489 {
	@Test
	public void testSyntaxErrorMessage() throws IOException {
		final String message = "hi";
		String [] issueCodes = { null, "issue" };
		String [][] issueDatas = { null, {null}, {"issue data"}};
		
		for (String[] issueData : issueDatas) {
			for (String issueCode : issueCodes) {
				SyntaxErrorMessage sem = new SyntaxErrorMessage(message, issueCode, issueData);
				ByteArrayOutputStream out = new ByteArrayOutputStream ();
				DataOutputStream dout = new DataOutputStream(out);
				SerializationUtil.writeSyntaxErrorMessage(dout, null, sem);
				dout.close();
				byte[] array = out.toByteArray();
				ByteArrayInputStream in = new ByteArrayInputStream(array); 
				DataInputStream din = new DataInputStream(in);
				SyntaxErrorMessage sem2 = SerializationUtil.readSyntaxErrorMessage(din, null);
				assertEquals(sem, sem2); 
			}
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream ();
		DataOutputStream dout = new DataOutputStream(out);
		SerializationUtil.writeSyntaxErrorMessage(dout, null, null);
		dout.close();
		byte[] array = out.toByteArray();
		ByteArrayInputStream in = new ByteArrayInputStream(array); 
		DataInputStream din = new DataInputStream(in);
		SyntaxErrorMessage readMessage = SerializationUtil.readSyntaxErrorMessage(din, null);
		assertNull(readMessage);
	}

}