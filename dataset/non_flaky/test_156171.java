class DummyClass_156171 {
	@Test
	public void testClinitOf() {
		Path cp = Paths.get("src", "test", "resources", "Clinit", "bin");
		G.reset();
		Options.v().set_prepend_classpath(true);
		Options.v().set_process_dir(Collections.singletonList(cp.toFile().getAbsolutePath()));
		Options.v().set_src_prec(Options.src_prec_class);
		Options.v().set_allow_phantom_refs(true);
		Options.v().set_ignore_resolving_levels(true);
		Options.v().setPhaseOption("cg.spark", "on");
		Options.v().setPhaseOption("cg.spark", "string-constants:true");
		Options.v().set_whole_program(true);
		Scene.v().loadNecessaryClasses();
		SootMethod mainMethod = Scene.v().getMainMethod();
		Scene.v().setEntryPoints(Collections.singletonList(mainMethod));
		PackManager.v().getPack("cg").apply();
		CallGraph cg = Scene.v().getCallGraph();
		boolean found = false;
		for (Edge edge : cg) {
			if (edge.getSrc().method().getSignature().equals("<soot.Main: void main(java.lang.String[])>")) {
				if (edge.getTgt().method().getSignature().equals("<soot.A: void <clinit>()>")) { // A1 is used in main
					found = true;
					break;
				}
			}
		}
		assertTrue(found);
		SootClass a1 = Scene.v().getSootClassUnsafe("soot.A1");
		SootClass a = Scene.v().getSootClassUnsafe("soot.A");
		assertTrue(a1 != null);
		List<String> clinits1 = new ArrayList<>();
		EntryPoints.v().clinitsOf(a1).forEach(e -> {
			clinits1.add(e.toString());
		});
		List<String> clinits = new ArrayList<>();
		EntryPoints.v().clinitsOf(a).forEach(e -> {
			clinits.add(e.toString());
		});
		assertEquals(clinits1, clinits);
	}

}