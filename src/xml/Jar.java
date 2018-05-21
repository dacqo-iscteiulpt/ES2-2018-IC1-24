package xml;

public class Jar {
	
	private String nome;
	public String getNome() {
		return nome;
	}

	public String getPath() {
		return path;
	}

	private String path;
	
	public Jar (String nome, String path) {
		this.nome = nome;
		this.path = path;
	}

	@Override
	public String toString() {
		return "Jar [nome=" + nome + ", path=" + path + "]";
	}
	
}
