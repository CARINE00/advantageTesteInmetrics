package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:sqlite:executions.db";

    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            Statement stmt = conn.createStatement();
            //Tabela execução de testes
            String createTableSQL = """
                    CREATE TABLE IF NOT EXISTS test_execution (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        scenario_name TEXT NOT NULL,
                        status TEXT NOT NULL,
                        timestamp TEXT NOT NULL
                    );
                    """;
            stmt.execute(createTableSQL);
            //Tabela execução de logs
            String createLogTable = """
                    CREATE TABLE IF NOT EXISTS execution_logs (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        log_text TEXT NOT NULL,
                        timestamp TEXT NOT NULL
                    );
                    """;
            stmt.execute(createLogTable);
            //Tabela execução de Relatorios
            String createReportTable = """
                    CREATE TABLE IF NOT EXISTS test_report (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        file_name TEXT NOT NULL,
                        content TEXT NOT NULL,
                        timestamp TEXT NOT NULL
                    );
                    """;
            stmt.execute(createReportTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertExecution(String scenarioName, String status) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String insertSQL = "INSERT INTO test_execution (scenario_name, status, timestamp) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
            pstmt.setString(1, scenarioName);
            pstmt.setString(2, status);
            pstmt.setString(3, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertLog(String logText) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO execution_logs (log_text, timestamp) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, logText);
            pstmt.setString(2, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertReport(String fileName, String content) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO test_report (file_name, content, timestamp) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, fileName);
            pstmt.setString(2, content);
            pstmt.setString(3, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Método auxiliar para ler arquivo e salvar diretamente como relatório
    public static void insertReportFromFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                insertLog("Arquivo de relatório não encontrado: " + filePath);
                return;
            }
            String fileName = path.getFileName().toString();
            String content = Files.readString(path);
            insertReport(fileName, content);
        } catch (IOException e) {
            insertLog("Erro ao ler arquivo de relatório: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
