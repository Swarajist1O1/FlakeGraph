class DummyClass_122624 {
    @Test
    public void translator_with_valid_parameters() {

        // Test simplest possible address
        Inet6Address original = (Inet6Address) InetAddresses.forString("2001:db8::1");
        Inet6Address prefix = (Inet6Address) InetAddresses.forString("fd00::");
        InetAddress translated = IPAddresses.prefixTranslate(original, prefix, 8);
        assertEquals("fd00:0:0:0:0:0:0:1", translated.getHostAddress());


        // Test an actual aws address we use
        original = (Inet6Address) InetAddresses.forString("2600:1f16:f34:5300:ccc6:1703:b7c2:369d");
        translated = IPAddresses.prefixTranslate(original, prefix, 8);
        assertEquals("fd00:0:0:0:ccc6:1703:b7c2:369d", translated.getHostAddress());

        // Test different subnet size
        translated = IPAddresses.prefixTranslate(original, prefix, 6);
        assertEquals("fd00:0:0:5300:ccc6:1703:b7c2:369d", translated.getHostAddress());
    }

}