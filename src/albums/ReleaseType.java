package albums;

public enum ReleaseType {
	FULL_LENGTH("Full-length"),
	EP("EP"), 
	DEMO("Demo"), 
	SINGLE("Single"), 
	LIVE_ALBUM("Live Album"), 
	COMPILATION("Compilation"), 
	SPLIT("Split"), 
	COLLABORATION("Collaboration"), 
	DEMO_EP("Demo EP"), 
	BOOTLEG("Bootleg");
	
	private String typeName;
	
	private ReleaseType(String name) {
		setTypeName(name);
	}
	
	public void setTypeName(String name) {
		this.typeName = name;
	}
	
	public String getTypeName() {
		return this.typeName;
	}
	
//	// Valida que las tem�ticas en el archivo concuerden con las de este enum.
//		// Tira un error si una tem�tica en el archivo no corresponde con las del enum.
//		public static Tematica toTematica(String nombre) throws NoExisteTematicaException {
//			nombre = nombre.toLowerCase()
//					.replace('�', 'a')
//					.replace('�', 'e')
//					.replace('�', 'i')
//					.replace('�', 'o')
//					.replace('�', 'u');
//
//			if (nombre.equals("aventura"))
//				return Tematica.AVENTURA;
//
//			if (nombre.equals("degustacion"))
//				return Tematica.DEGUSTACION;
//
//			if (nombre.equals("paisaje"))
//				return Tematica.PAISAJE;
//
//			if (nombre.equals("entretenimiento"))
//				return Tematica.ENTRETENIMIENTO;
//
//			if (nombre.equals("carrera"))
//				return Tematica.CARRERA;
//
//			if (nombre.equals("extremo"))
//				return Tematica.EXTREMO;
//
//			throw new NoExisteTematicaException("La tematica \"" + nombre + "\" no existe!");
//		}
}
