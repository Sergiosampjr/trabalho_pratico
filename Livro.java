public class Livro {
    private int id;
    private String titulo;
    private int autorId;

    // Construtor
    public Livro(int id, String titulo, int autorId) {
        this.id = id;
        this.titulo = titulo;
        this.autorId = autorId;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int autorId) {
        this.autorId = autorId;
    }
}