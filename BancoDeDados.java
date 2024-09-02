import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BancoDeDados {
    private Connection conexao;

    public BancoDeDados() {
        try {
            // Conexão com o banco de dados
            conexao = DriverManager.getConnection("jdbc:sqlite:biblioteca.db");
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    // Método para adicionar um livro
    public void adicionarLivro(Livro livro) {
        try (PreparedStatement stmt = conexao.prepareStatement(
                "INSERT INTO Livros (titulo, autor_id) VALUES (?, ?)"
        )) {
            stmt.setString(1, livro.getTitulo());
            stmt.setInt(2, livro.getAutorId());
            stmt.executeUpdate();
            System.out.println("Livro adicionado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar livro: " + e.getMessage());
        }
    }

    // Método para remover um livro
    public void removerLivro(int id) {
        try (PreparedStatement stmt = conexao.prepareStatement(
                "DELETE FROM Livros WHERE id = ?"
        )) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Livro removido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao remover livro: " + e.getMessage());
        }
    }

    // Método para atualizar um livro
    public void atualizarLivro(Livro livro) {
        try (PreparedStatement stmt = conexao.prepareStatement(
                "UPDATE Livros SET titulo = ?, autor_id = ? WHERE id = ?"
        )) {
            stmt.setString(1, livro.getTitulo());
            stmt.setInt(2, livro.getAutorId());
            stmt.setInt(3, livro.getId());
            stmt.executeUpdate();
            System.out.println("Livro atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar livro: " + e.getMessage());
        }
    }

    // Método para pesquisar um livro por ID
    public Livro pesquisarLivroPorId(int id) {
        try (PreparedStatement stmt = conexao.prepareStatement(
                "SELECT * FROM Livros WHERE id = ?"
        )) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Crie o objeto Livro usando o construtor correto
                Livro livro = new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getInt("autor_id"));
                return livro;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar livro: " + e.getMessage());
        }
        return null;
    }

    // Método para inserir vários livros
    public void adicionarLivros(List<Livro> livros) {
        try (PreparedStatement stmt = conexao.prepareStatement(
                "INSERT INTO Livros (titulo, autor_id) VALUES (?, ?)"
        )) {
            for (Livro livro : livros) {
                stmt.setString(1, livro.getTitulo());
                stmt.setInt(2, livro.getAutorId());
                stmt.addBatch();
            }
            stmt.executeBatch();
            System.out.println("Livros adicionados com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar livros: " + e.getMessage());
        }
    }

    // Método para pesquisar livros por nome do autor
    public List<Livro> pesquisarLivrosPorAutor(String nome) {
        List<Livro> livros = new ArrayList<>();
        try (PreparedStatement stmt = conexao.prepareStatement(
                "SELECT * FROM Livros WHERE autor_id IN (SELECT id FROM Autores WHERE nome LIKE ?)"
        )) {
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Crie o objeto Livro usando o construtor correto
                Livro livro = new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getInt("autor_id"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar livros: " + e.getMessage());
        }
        return livros;
    }
}
