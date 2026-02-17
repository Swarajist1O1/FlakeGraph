class DummyClass_99776 {
    @Test
    public void testDroppedMessages()
    {
        Verb verb = Verb.READ_REQ;

        for (int i = 1; i <= 5000; i++)
            messagingService.metrics.recordDroppedMessage(verb, i, MILLISECONDS, i % 2 == 0);

        List<String> logs = new ArrayList<>();
        messagingService.metrics.resetAndConsumeDroppedErrors(logs::add);
        assertEquals(1, logs.size());
        Pattern regexp = Pattern.compile("READ_REQ messages were dropped in last 5000 ms: (\\d+) internal and (\\d+) cross node. Mean internal dropped latency: (\\d+) ms and Mean cross-node dropped latency: (\\d+) ms");
        Matcher matcher = regexp.matcher(logs.get(0));
        assertTrue(matcher.find());
        assertEquals(2500, Integer.parseInt(matcher.group(1)));
        assertEquals(2500, Integer.parseInt(matcher.group(2)));
        assertTrue(Integer.parseInt(matcher.group(3)) > 0);
        assertTrue(Integer.parseInt(matcher.group(4)) > 0);
        assertEquals(5000, (int) messagingService.metrics.getDroppedMessages().get(verb.toString()));

        logs.clear();
        messagingService.metrics.resetAndConsumeDroppedErrors(logs::add);
        assertEquals(0, logs.size());

        for (int i = 0; i < 2500; i++)
            messagingService.metrics.recordDroppedMessage(verb, i, MILLISECONDS, i % 2 == 0);

        logs.clear();
        messagingService.metrics.resetAndConsumeDroppedErrors(logs::add);
        assertEquals(1, logs.size());
        matcher = regexp.matcher(logs.get(0));
        assertTrue(matcher.find());
        assertEquals(1250, Integer.parseInt(matcher.group(1)));
        assertEquals(1250, Integer.parseInt(matcher.group(2)));
        assertTrue(Integer.parseInt(matcher.group(3)) > 0);
        assertTrue(Integer.parseInt(matcher.group(4)) > 0);
        assertEquals(7500, (int) messagingService.metrics.getDroppedMessages().get(verb.toString()));
    }

}