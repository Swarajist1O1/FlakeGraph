class DummyClass_98655 {
    // @Test
    public void test_raw2() throws Throwable {
        File src = new File("H://main_qt");
        File dst = new File("H://cache.tmp");
        RangeRange rangeRange = new RangeRange(0, src.length());
        // RawView.writeFileRange(src, new FileOutputStream(dst), rangeRange);
        //
        // System.out.println(Lang.digest("md5", src));
        // System.out.println(Lang.digest("md5", dst));

        List<RangeRange> rs = new ArrayList<RawView.RangeRange>();
        RawView.parseRange("bytes=0-,-1000000,22222-22222222222", rs, Long.MAX_VALUE);
        System.out.println(Json.toJson(rs));

        src = new File("H://raw");
        FileOutputStream out = new FileOutputStream(src);
        for (int i = 0; i < 255; i++) {
            out.write(i);
        }
        out.flush();
        out.close();

        rs = new ArrayList<RawView.RangeRange>();
        RawView.parseRange("bytes=0-127", rs, 256);
        rangeRange = rs.get(0);
        RawView.writeFileRange(src, new FileOutputStream(dst), rangeRange);
        System.out.println(dst.length());
        FileInputStream in = new FileInputStream(dst);
        for (int i = 0; i < 128; i++) {
            if (in.read() != i) {
                System.out.println("ERR");
            }
        }
        Streams.safeClose(in);

        rs = new ArrayList<RawView.RangeRange>();
        RawView.parseRange("bytes=128-", rs, 256);
        rangeRange = rs.get(0);
        RawView.writeFileRange(src, new FileOutputStream(dst), rangeRange);
        in = new FileInputStream(dst);
        for (int i = 0; i < 128; i++) {
            if (in.read() != (i + 128)) {
                System.out.println("ERR");
            }
        }
        Streams.safeClose(in);

        rs = new ArrayList<RawView.RangeRange>();
        RawView.parseRange("bytes=-64", rs, 256);
        rangeRange = rs.get(0);
        RawView.writeFileRange(src, new FileOutputStream(dst), rangeRange);
        in = new FileInputStream(dst);
        for (int i = 0; i < 64; i++) {
            if (in.read() != (i + 128 + 64)) {
                System.out.println("ERR");
            }
        }
        Streams.safeClose(in);

        System.out.println("---------------------------END");
    }

}