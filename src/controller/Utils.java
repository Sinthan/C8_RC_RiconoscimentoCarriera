package controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import interfacce.UserInterface;
import model.SystemAttribute;


public class Utils {
	public Utils() {}

	/**
	 * Exposes the function to encrypt the password.
	 * 
	 * @param passwordToHash is the password to be encrypted.
	 * @return the encrypted password.
	 */
	public String generatePwd(String passwordToHash) {
		String generatedPassword = null;
		String salt = "englishvalidation";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(salt.getBytes(StandardCharsets.UTF_8));
			byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	/**
	 * method getRequestState.
	 * @param idRequest id request
	 * @return idState
	 */
	public Integer getRequestState(Integer idRequest) {
		Integer idState = 0;
		Connection conn = new DbConnection().getInstance().getConn();
		if (conn != null) {
			try {
				PreparedStatement stmt =
						conn.prepareStatement("SELECT fk_state FROM request WHERE id_request = ?");
				stmt.setInt(1, idRequest);
				ResultSet r = stmt.executeQuery();
				if (!r.wasNull()) {
					r.next();
					idState = r.getInt("fk_state");
				}
			} catch (Exception e) {
				idState = 0;
				System.out.println(e.getMessage());
			}
		}

		return idState;
	}

	/**
	 * method getLastUserRequestPartiallyCompleted.
	 * @param s session.
	 * @return idRequest
	 */
	public Integer getLastUserRequestPartiallyCompleted(HttpSession s) {
		Integer idRequest = 0;
		Connection conn = new DbConnection().getInstance().getConn();
		if (conn != null) {
			try {
				UserInterface user = (UserInterface) s.getAttribute("user");

				PreparedStatement stmt = conn
						.prepareStatement("SELECT id_request FROM request WHERE fk_user = ? AND fk_state = ?");
				stmt.setString(1, user.getEmail());
				stmt.setInt(2,
						Integer.parseInt(new SystemAttribute().getValueByKey("request-partially-completed")));
				ResultSet r = stmt.executeQuery();
				if (!r.wasNull()) {
					r.next();
					idRequest = r.getInt("id_request");
				}
			} catch (Exception e) {
				idRequest = 0;
			}
		}

		return idRequest;
	}

	/**
	 * @return	the absolute path of the project folder, or null if an error occurred
	 * @author	Gianluca Rossi
	 */
	public static String getProjectPath() {
		String unixExtraPathFirstFolder = "/.metadata/";
		String windowsExtraPathFirstFolder = "\\.metadata\\";
		// Gets a path that identifies a temporary folder inside the workspace
		String extendedPath = System.getProperty("catalina.base");
		// Determines the starting index of the extra path depending on the user OS (inferred from the slash)
		int slashFound = extendedPath.indexOf("/");
		int extraPathStartIndex;
		if (slashFound == -1) {
			extraPathStartIndex = extendedPath.indexOf(windowsExtraPathFirstFolder);
		} else {
			extraPathStartIndex = extendedPath.indexOf(unixExtraPathFirstFolder);
		}
		// Extracts the workspace path
		String workspacePath = extendedPath.substring(0, extraPathStartIndex);
		if (workspacePath.equals("")) {
			return null;
		}
		// Append the project folder to the path
		String finalPath = workspacePath + "/C8_RC_RiconoscimentoCarriera";
		return finalPath;
	}

	/**
	 * Extracts the path of the file's parent directory
	 * @param	filePath		the path pointing to the file (with the file name included)
	 * @return					the path of the parent folder of the file, or null if an error occurred
	 * @author	Gianluca Rossi
	 */
	public static String getParentDirectoryFromFilePath(String filePath) {
		String parentDirectory = "";
		// Determines the starting index of the file name path (inferred from the last slash)
		int filenameStartIndex = filePath.lastIndexOf("\\");
		// Extracts the parent directory path if possible
		if (filenameStartIndex != -1) {
			parentDirectory = filePath.substring(0, filenameStartIndex);
		} else {
			int filenameStartIndexUnix = filePath.lastIndexOf("/");
			if (filenameStartIndexUnix != -1) {
				parentDirectory = filePath.substring(0, filenameStartIndexUnix);
			}
		}
		return parentDirectory;
	}
	
	/**
	 * Formats the date to the italian format
	 * @param	date			the unformatted date
	 * @return					the given date in the format dd/mm/yyyy
	 * @author	Gianluca Rossi
	 */
	public static String getFormattedDate(Date date) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
	}

}
