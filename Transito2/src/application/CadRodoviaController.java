package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Rodovia;
import util.Conexao;

public class CadRodoviaController {
	
	@FXML TextField txtNome;
	@FXML TextField txtKm;
	@FXML TableView<Rodovia> tbl;
	@FXML TableColumn<Rodovia, Number> colId;
	@FXML TableColumn<Rodovia, String> colNome;
	@FXML TableColumn<Rodovia, Number> colKm;
	
	private ArrayList<Rodovia> rodovias = new ArrayList<Rodovia>();
	
	@FXML
	public void initialize() {
		colId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colKm.setCellValueFactory(cellData -> cellData.getValue().kmProperty());
		buscaRodovias();
	}
	
	@FXML
	public void grava() {
		insereRodovia();
	}
	
	
	private void insereRodovia() {
		Rodovia r = lerTela();
		String sql = "insert into rodovia(nome, km) values (?,?)";
		try {
			Connection conn = Conexao.conectaSqlite();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, r.getNome());
			ps.setInt(2, r.getKm());
			ps.executeUpdate();
			conn.close();
			buscaRodovias();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Rodovia lerTela() {
		Rodovia r = new Rodovia();
		r.setNome(txtNome.getText());
		r.setKm(Integer.parseInt(txtKm.getText()));
		return r;
	}
	
	private void buscaRodovias() {
		rodovias = new ArrayList<Rodovia>();
		String sql = "select * from rodovia order by km";
		try {
			Connection conn = Conexao.conectaSqlite();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Rodovia r = new Rodovia();
				r.setId(rs.getInt("id"));
				r.setNome(rs.getString("nome"));
				r.setKm(rs.getInt("km"));
				rodovias.add(r);
			}
			conn.close();
			tbl.setItems(FXCollections.observableArrayList(rodovias));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
