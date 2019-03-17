package com.shortener.url.service;

import org.json.JSONObject;

import com.shortener.url.model.Url;
import com.shortener.url.model.User;

public class MessageService {

	private static final String SUCCESS_KEY = "success";

	private static final String DESCRIPTION_KEY = "description";

	private static final String PASSWORD_KEY = "password";

	public static String getForShortUrl(Url url) {
		return new JSONObject().put("shortUrl", url.getShortUrl()).toString();
	}

	public static String getForStatistic(User user) {
		JSONObject json = new JSONObject();
		user.getMyUrlList().forEach((shortUrl, url) -> json.put(url.getRealUrl(), url.getNumberOfVisits()));
		return json.toString();
	}

	public static String getApprovedAccount(String password) {
		return new JSONObject().put(SUCCESS_KEY, true).put(DESCRIPTION_KEY, "Your account is opened")
				.put(PASSWORD_KEY, password).toString();
	}

	public static String getRejectedAccount() {
		return new JSONObject().put(SUCCESS_KEY, false)
				.put(DESCRIPTION_KEY, "Account with that accountId already exist").toString();
	}

	private static JSONObject registerUserExample() {
	return new JSONObject()
			.put("accountId","nekakvVasUsername");
	}

	private static JSONObject registerUrlExample() {
		return new JSONObject()
				.put("realUrl","putanju koju želite skratiti")
				.put("redirectType","301|302");
		}

	private static JSONObject loginExample() {
		 
		return registerUserExample()
				.put("password", "ovdjeUnosuteVasIzgenerisaniPassword");
	}
	public static String getHelpPageInfo() {
		return "DOBRO DOSLI NA POMOCNU STRANICU "
				+ "\n\n-Korišteni su sledeći alati:spring boot, spring security, spring RESTful, maven, lombok"
				+ "\n\n-Namjena aplikacijE je da  pravite vlastite, skraćene url-ove, na osnovu pravih"
				+ "\n\n-Za provjeru rada aplikacije preporučujem postman,"
				+ "\ngdje se morate registrovat prvo kao korisnik, potom ulogovati, gdje dobijate vaš token...koji koristite u daljim radnjama "
				+ "\n\n-Svi pozivi idu preko porta 8090, koji je u propertisima postavljen"
				+ "\n\n-POSTUPAK"
				+ "\n\n-REGISTRACIJA se nalaz na putanji :http://localhost:8090/account, metoda POST"
				+ "\nmorate obavezno unjeti accountId u JSON formatu, sifru dobijate izgenerisanu kao odgovr na poziv,"
				+ "\nPrimjer:"+registerUserExample() 
				+ "\n\n-LOGOVANJE se nalaz na putanji :http://localhost:8090/login, metoda POST"
				+ "\nPrimjer:"+loginExample() +","
				+ "\n\n-REGISTRACIJA URL-a kojeg želite skratiti se nalaz na putanji :http://localhost:8090/register, metoda POST"
				+ "\nPrimjer:"+registerUrlExample() 
				+ "\nobavezno u postmanu podesiti Authorisation->TYPE->Bearer Token-->unjeti token koji smo dobili na logovanju"
				+ "\n\n-PROVJERA VASE STATISTIKE se nalaz na putanji :http://localhost:8090/statistic/{AccountId}, metoda GET"
				+ "\nPrimjer: http://localhost:8090/statistic/accountId"
				+ "\nobavezno u postmanu podesiti Authorisation->TYPE->Bearer Token-->unjeti token koji smo dobili na logovanju";

	}
}