class DummyClass_91568 {
    @Test
    public void testSendEmail() throws IOException {

        KylinConfig config = KylinConfig.getInstanceFromEnv();

        MailService mailservice = new MailService(config);
        boolean sent = sendTestEmail(mailservice);
        assert sent;

        System.setProperty("kylin.job.notification-enabled", "false");
        // set kylin.job.notification-enabled=false, and run again, this time should be no mail delivered
        mailservice = new MailService(config);
        sent = sendTestEmail(mailservice);
        assert !sent;

    }

}