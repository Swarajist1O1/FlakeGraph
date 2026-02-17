class DummyClass_98247 {
    @Test
    public void testEmptyGlobRestriction3()throws Exception{
        Node child2 = superuser.getNode(path).addNode("child2");
        String childNPath2 = child2.getPath();
        superuser.save();

        try {
            Group group1 = getTestGroup();
            Group group2 = getUserManager(superuser).createGroup("group2");
            group2.addMember(testUser);
            Group group3 = getUserManager(superuser).createGroup("group3");
            superuser.save();

            assertTrue(group1.isDeclaredMember(testUser));
            assertTrue(group2.isDeclaredMember(testUser));
            assertFalse(group3.isDeclaredMember(testUser));

            Privilege[] read = privilegesFromName(Privilege.JCR_READ);

            withdrawPrivileges(path, group1.getPrincipal(), read, Collections.EMPTY_MAP);
            Map<String, Value> emptyStringRestriction = new HashMap<String, Value>(getRestrictions(superuser, path));
            emptyStringRestriction.put(AccessControlConstants.P_GLOB.toString(), vf.createValue(""));
            givePrivileges(path, group1.getPrincipal(), read, emptyStringRestriction);

            withdrawPrivileges(childNPath, group2.getPrincipal(), read, Collections.EMPTY_MAP);
            emptyStringRestriction = new HashMap<String, Value>(getRestrictions(superuser, childNPath));
            emptyStringRestriction.put(AccessControlConstants.P_GLOB.toString(), vf.createValue(""));
            givePrivileges(childNPath, group2.getPrincipal(), read, emptyStringRestriction);

            withdrawPrivileges(childNPath2, group3.getPrincipal(), read, Collections.EMPTY_MAP);
            emptyStringRestriction = new HashMap<String, Value>(getRestrictions(superuser, childNPath2));
            emptyStringRestriction.put(AccessControlConstants.P_GLOB.toString(), vf.createValue(""));
            givePrivileges(childNPath2, group3.getPrincipal(), read, emptyStringRestriction);

            // NOTE: test-session is created here and is expected to reflect the
            // group membership changes made above.
            Session testSession = getTestSession();
            assertTrue(testSession.nodeExists(path));
            assertTrue(testSession.nodeExists(childNPath));
            assertFalse(testSession.nodeExists(childNPath2));
        } finally {
            Authorizable g2 = getUserManager(superuser).getAuthorizable("group2");
            if (g2 != null) {
                g2.remove();
            }
            Authorizable g3 = getUserManager(superuser).getAuthorizable("group3");
            if (g3 != null) {
                g3.remove();
            }
            superuser.save();
        }
    }

}