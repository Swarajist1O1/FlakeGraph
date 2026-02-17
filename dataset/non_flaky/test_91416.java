class DummyClass_91416 {
    @TestGroup(enabled = true, sysProperty = ESRestTestCase.TESTS_REST)
            public int compare(RestTestCandidate o1, RestTestCandidate o2) {
                return o1.getTestPath().compareTo(o2.getTestPath());
            }

}