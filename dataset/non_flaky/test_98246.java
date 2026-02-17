class DummyClass_98246 {
    @Test
    public void testEmptyGlobRestriction2()throws Exception{
        Node grandchild = superuser.getNode(childNPath).addNode("child");
        String ccPath = grandchild.getPath();
        superuser.save();

        // first deny access to 'path' (read-access is granted in the test setup)
        Privilege[] read = privilegesFromName(Privilege.JCR_READ);
        withdrawPrivileges(path, read, Collections.EMPTY_MAP);

        Session testSession = getTestSession();
        assertFalse(testSession.nodeExists(path));
        assertFalse(canGetNode(testSession, path));
        assertFalse(testSession.nodeExists(childNPath));
        assertFalse(canGetNode(testSession, childNPath));
        assertFalse(testSession.nodeExists(ccPath));
        assertFalse(canGetNode(testSession, ccPath));
        assertFalse(testSession.propertyExists(childNPath + '/' + JcrConstants.JCR_PRIMARYTYPE));

        Map<String, Value> emptyStringRestriction = new HashMap<String, Value>(getRestrictions(superuser, path));
        emptyStringRestriction.put(AccessControlConstants.P_GLOB.toString(), vf.createValue(""));

        givePrivileges(path, read, emptyStringRestriction);
        assertTrue(testSession.nodeExists(path));
        assertTrue(canGetNode(testSession, path));
        assertFalse(testSession.nodeExists(childNPath));
        assertFalse(canGetNode(testSession, childNPath));
        assertFalse(testSession.nodeExists(ccPath));
        assertFalse(canGetNode(testSession, ccPath));
        assertFalse(testSession.propertyExists(childNPath + '/' + JcrConstants.JCR_PRIMARYTYPE));
    }

}