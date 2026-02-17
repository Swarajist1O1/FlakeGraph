class DummyClass_98244 {
    @Test
    public void testQuery() throws Exception {
        if (Constants.WINDOWS) {
            return;
        }
        for (int i = 0; i < 100; i++) {
            session.getRootNode().addNode("node" + i, "nt:unstructured");
        }
        session.save();
        final QueryManager qm = session.getWorkspace().getQueryManager();
        final AtomicBoolean stop = new AtomicBoolean(false);
        final List<Exception> exceptions = Collections.synchronizedList(
                new ArrayList<Exception>());
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!stop.get() && exceptions.isEmpty()) {
                    try {
                        // execute query
                        String stmt = "//*[@jcr:primaryType='nt:unstructured']";
                        qm.createQuery(stmt, Query.XPATH).execute();
                    } catch (RepositoryException e) {
                        if (Constants.SUN_OS) {
                            // on Solaris it's OK when the root cause
                            // of the exception is an InterruptedIOException
                            // the underlying file is not closed
                            Throwable t = e;
                            while (t.getCause() != null) {
                                t = t.getCause();
                            }
                            if (!(t instanceof InterruptedIOException)) {
                                exceptions.add(e);
                            }
                        } else {
                            exceptions.add(e);
                        }
                    }
                }
            }

}