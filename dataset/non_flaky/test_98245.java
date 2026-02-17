class DummyClass_98245 {
    @Test
    public void testEmptyGlobRestriction()throws Exception{
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

        Map<String, Value> emptyStringRestriction = new HashMap<String, Value>(getRestrictions(superuser, childNPath));
        emptyStringRestriction.put(AccessControlConstants.P_GLOB.toString(), vf.createValue(""));

        givePrivileges(childNPath, read, emptyStringRestriction);
        assertFalse(testSession.nodeExists(path));
        assertFalse(canGetNode(testSession, path));
        assertTrue(testSession.nodeExists(childNPath));
        assertTrue(canGetNode(testSession, childNPath));
        assertFalse(testSession.nodeExists(ccPath));
        assertFalse(canGetNode(testSession, ccPath));
        assertFalse(testSession.propertyExists(childNPath + '/' + JcrConstants.JCR_PRIMARYTYPE));

        givePrivileges(ccPath, read, Collections.EMPTY_MAP);
        assertTrue(testSession.nodeExists(ccPath));
        assertTrue(canGetNode(testSession, ccPath));
        assertTrue(testSession.propertyExists(ccPath + '/' + JcrConstants.JCR_PRIMARYTYPE));
    }

}