package wone.sn;

public class EtudiantEtranger extends Etudiant {
	private String pays;

	public EtudiantEtranger(int id, String name, String firstName, String email, String address, String dateOfBirth,
			String pays) {
		super(id, name, firstName, email, address, dateOfBirth);
		this.pays = pays;
		// TODO Auto-generated constructor stub
	}

	public String getPays() {
		return pays;
	}

}
