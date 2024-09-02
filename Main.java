import java.util.List;
import java.util.ArrayList; 
public class Main {
    public static void main(String[] args) {
        BancoDeDados bd = new BancoDeDados();

        // Adicionar livros
        Livro livro1 = new Livro(1, "Dom Casmurro", 1);
        Livro livro2 = new Livro(2, "O Alienista", 1);
        Livro livro3 = new Livro(3, "O Tempo e o Vento", 2);
        bd.adicionarLivro(livro1);
        bd.adicionarLivro(livro2);
        bd.adicionarLivro(livro3);

        // Adicionar vários livros
        List<Livro> livros = new ArrayList<>();
        livros.add(new Livro(4, "O Pequeno Príncipe", 3));
        livros.add(new Livro(5, "A Montanha Mágica", 4));
        bd.adicionarLivros(livros);

        // Pesquisar livros por nome do autor
        List<Livro> livrosDoMachado = bd.pesquisarLivrosPorAutor("Machado de Assis");
        System.out.println("Livros de Machado de Assis:");
        livrosDoMachado.forEach(livro -> System.out.println(livro.getTitulo()));

        // Remover um livro
        bd.removerLivro(2);

        // Atualizar um livro
        livro1.setTitulo("Dom Casmurro (Edição Especial)");
        bd.atualizarLivro(livro1);

        // Pesquisar um livro por ID
        Livro livroEncontrado = bd.pesquisarLivroPorId(1);
        System.out.println("Livro encontrado: " + livroEncontrado.getTitulo());
    }
}