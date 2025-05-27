package Textos;

public class Textos {
	public static void intro() {
		System.out.println("Te encuentras en casa, merendando unos dorayakis mientras miras absorto la televisión sin ver realmente nada.\n"
				+ "Solo estás demorando el momento de ponerte a hacer los deberes que te mandó el Sensi Francisco-san.\n"
				+ "\nDe repente, tu televisión cobra vida: Sewashi, el tataranieto de Nobita, aparece con expresión de preocupación en mitad del salón\n"
				+ "Te cuenta que en el futuro, es decir, su presente. El mundo está al borde de la desaparición.\n"
				+ "Tokio se ha convertido en una ciudad irreconocible, cubierta de polvo tóxico y envuelta por la contaminación.\n"
				+ "\nLas estaciones han desaparecido, dejando solo un verano abrasador y un invierno implacable.\n"
				+ "El cambio climático no solo ha afectado a la vida tal y como la conociamos, sino también a los gadgets de Doraemon.\n"
				+ "La puerta mágica es inestable, el gorrocóptero no puede volar, y hasta Dorami está fallando.");
	}
	
	public static String introduccionNobita (String guardian) {
		return "BOSQUE TOXICO DE NARA\n"
				+ "\nTe adentras en el Bosque Tóxico de Nara, un lugar que una vez fue un paraíso natural,\n"
				+ "ahora transformado en un páramo oscuro por los desechos tóxicos, polución y contaminación.\n"
				+ "\nTropiezas y caes. Cuando levantas la vista ves frente a ti lo que un día un majestuoso ciervo de Nara.\n"
				+ "Ahora es un " + guardian + ".\n"
				+ "\nEl " + guardian + " clava sus ojos rojos en ti, y se prepara para lanzar una fuerte cornamenta.\n"
				+ "\nEste lugar ha sido corrompido, y tú también sientes el peso de su amenaza.\n"
				+ "\nRecuerdas tu misión y sabes que debes enfrentarte al " + guardian +"\n"
				+ "un artefacto poderoso que puede disipar la oscuridad y restaurar la esperanza en este mundo desolado.\n"
				+ "\n¿Estás listo para el desafío?";
	}
	
	public static String introduccionShizuka(String guardian) {
		return "CASTILLO DE HIMEJI\n"
				+ "El legendario Castillo de Himeji, antaño una joya de arquitectura y poder, ahora es un reflejo de su antigua gloria.\n"
				+ "Sus torres estan derruidas, y los muros cubiertos de moho negro.\n"
				+ "\nExplorando los pasillos cubiertos de polvo, te detienes delante ante una figura imponente: un samurái esquelético.\n"
				+ "Tiene la armadura corroída y la katana esta oxidada.\n"
				+ "Derrepente Sus ojos rojos brillan con una intensidad antinatural, y su presencia exuda un aura de tragedia y furia contenida.\n"
				+ "\nUn escalofrio te recorre la espalda... " + guardian + " acaba de desfundar la katana.\n"
				+ "Te esta retando a un combate a muerte\n"
				+ "Estes lista o no, debes aceptar el duelo.";
	}
	
	public static String introduccionSuneo(String guardian) {
		return "CALLES DE GINZA\n"
				+ "\nTe encuentras en Ginza, el distrito más exclusivo de Japón.\n"
				+ "Los rascacielos dorados y las calles impecablemente pulidas parecen un sueño.\n"
				+ "Aparentemente todo esta como siempre, pero en el aire flota una sensación inquietante: el peso de la avaricia y el consumo desmedido.\n"
				+ "\nAun asi sigues paseando y te detienes a mirar unos escaparares, cuando en el reflejo de uno de ellos ves una colosal estatua de oro.\n"
				+ "Es una representación de ti mismo, pero exagerada y grotesca, es un " + guardian + ".\n"
				+ "\nLa personificación del capitalismo extremo y del deseo insaciable por bienes materiales.\n"
				+ "La estatua está devorando todo lo que te rodea.\n"
				+ "\nDebes detenerla o sera tu fin";
	}

	public static String introduccionGigante(String guardian) {
		return "EL TEMPLO DE KINKAKU-JI\n"
				+ "\nEl majestuoso Templo Kinkaku-ji, antaño un símbolo de paz y belleza, ahora yace sumido en la decadencia.\n"
				+ "Sus doradas paredes están manchadas de ceniza y corrosión. El aire es sofocante y un silencio sepulcral envuelve el lugar\n"
				+ "\nEn el corazón del templo, la estatua de Buda, que antes representaba la paz se ha transformado en un monstruoso " + guardian + "\n"
				+ "Su mirada iluminada por un fulgor rojo resplandece con furia,y su imponente presencia llena la sala.\n"
				+ "\nSu rostro sereno ha sido reemplazado por una expresión de furia.\n"
				+ "La estancia se ha convertido en un campo de batalla.\n"
				+ "\nPara superar este reto tendrás que enfrentarte a la furia de + guardian";
	}
}
