class DummyClass_112764 {
	@Test
	public void testDeleteAll() throws Exception {
		DeleteBuilder<Foo, Integer> stmtb = new DeleteBuilder<Foo, Integer>(databaseType, baseFooTableInfo, null);
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ");
		databaseType.appendEscapedEntityName(sb, baseFooTableInfo.getTableName());
		assertEquals(sb.toString(), stmtb.prepareStatementString());
	}

}