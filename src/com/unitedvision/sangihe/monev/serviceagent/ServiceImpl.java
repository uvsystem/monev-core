package com.unitedvision.sangihe.monev.serviceagent;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.unitedvision.sangihe.configuration.test.UtilityService;
import com.unitedvision.sangihe.monev.entity.UnitKerja;
import com.unitedvision.sangihe.monev.entity.UnitKerja.TipeUnitKerja;
import com.unitedvision.sangihe.monev.serviceagent.entity.Operator;
import com.unitedvision.sangihe.monev.serviceagent.entity.Operator.Role;
import com.unitedvision.sangihe.monev.serviceagent.entity.Pegawai;
import com.unitedvision.sangihe.monev.serviceagent.entity.Token;
import com.unitedvision.sangihe.monev.serviceagent.entity.Token.StatusToken;
import com.unitedvision.sangihe.monev.util.EntityRestMessage;
import com.unitedvision.sangihe.monev.util.PasswordWrapper;
import com.unitedvision.sangihe.monev.util.RestMessage;
import com.unitedvision.sangihe.monev.util.RestMessage.Type;

public class ServiceImpl implements Service, UtilityService {

	private RestTemplate restTemplate = new RestTemplate();
	private ObjectCreator objectCreator;
	
	//private final String host = "https://core-unitedvision.whelastic.net";
	 private final String host = "http://localhost:8080";
	// private final String host = "https://sistem.sangihe.go.id";
	// private final String host = "https://sangihe.whelastic.net";
	
	public ServiceImpl() {
		super();
		objectCreator = ObjectCreator.getInstance();
	}
	 
	@SuppressWarnings("unchecked")
	@Override
	public Token create(String nip, String password) throws ServiceException {
		EntityRestMessage<Token> message;
		
//		String auth = createBasicAuthentication(nip, password);
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.set("Authorization", auth);
//		
//		HttpEntity<String> entity = new HttpEntity<String>(headers);

		message = restTemplate.postForObject("{host}/ehrm/token/{nip}", new PasswordWrapper(password), EntityRestMessage.class, host, nip);
		
		return objectCreator.createToken((Map<String, Object>)message.getModel());
	}

	@Override
	public void lock(String tokenString) {
		restTemplate.put("{host}/ehrm/token/{tokenString}", null, host, tokenString);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Token get(String tokenString) throws ServiceException {
		EntityRestMessage<Token> message;

		message = restTemplate.getForObject("{host}/ehrm/token/{tokenString}", EntityRestMessage.class, host, tokenString);
		
		return objectCreator.createToken((Map<String, Object>)message.getModel());
	}
	
	@Override
	public void hapus(HttpHeaders headers) throws ServiceException {
		ResponseEntity<RestMessage> responseEntity = restTemplate.exchange("{host}/ehrm/token", HttpMethod.DELETE, new HttpEntity<Token>(null, headers), RestMessage.class, host);
		
		if (!responseEntity.getBody().getTipe().equals(Type.SUCCESS))
			throw new ServiceException(responseEntity.getBody().getMessage());
	}
	
	@Override
	public void tambah(Pegawai pegawai, Long idUnitKerja, HttpHeaders headers) throws ServiceException {
		ResponseEntity<RestMessage> responseEntity = restTemplate.exchange("{host}/ehrm/pegawai/{unitKerja}", HttpMethod.POST, new HttpEntity<Pegawai>(pegawai, headers), RestMessage.class, host, idUnitKerja);
		
		if (!responseEntity.getBody().getTipe().equals(Type.SUCCESS))
			throw new ServiceException(responseEntity.getBody().getMessage());
	}

	@Override
	public void hapus(Pegawai pegawai, HttpHeaders headers) throws ServiceException {
		ResponseEntity<RestMessage> responseEntity = restTemplate.exchange("{host}/ehrm/pegawai/{nip}", HttpMethod.DELETE, new HttpEntity<Pegawai>(null, headers), RestMessage.class, host, pegawai.getNip());
		
		if (!responseEntity.getBody().getTipe().equals(Type.SUCCESS))
			throw new ServiceException(responseEntity.getBody().getMessage());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Pegawai getPegawai(String nip, HttpHeaders headers) throws ServiceException {
		ResponseEntity<EntityRestMessage> responseEntity = restTemplate.exchange("{host}/ehrm/pegawai/{nip}", HttpMethod.GET, new HttpEntity<UnitKerja>(null, headers), EntityRestMessage.class, host, nip);
		
		if (!responseEntity.getBody().getTipe().equals(Type.ENTITY))
			throw new ServiceException(responseEntity.getBody().getMessage());

		Map<String, Object> maps = (Map<String, Object>) responseEntity.getBody().getModel();
		return objectCreator.createPegawai(maps);
	}
	
	@Override
	public void tambah(UnitKerja unitKerja, HttpHeaders headers) throws ServiceException {
		ResponseEntity<RestMessage> responseEntity = restTemplate.exchange("{host}/ehrm/satker", HttpMethod.POST, new HttpEntity<UnitKerja>(unitKerja, headers), RestMessage.class, host);
		
		if (!responseEntity.getBody().getTipe().equals(Type.SUCCESS))
			throw new ServiceException(responseEntity.getBody().getMessage());
	}
	
	@Override
	public void hapus(UnitKerja unitKerja, HttpHeaders headers) throws ServiceException {
		ResponseEntity<RestMessage> responseEntity = restTemplate.exchange("{host}/ehrm/satker/{unitKerja}", HttpMethod.DELETE, new HttpEntity<UnitKerja>(null, headers), RestMessage.class, host, unitKerja.getSingkatan());
		
		if (!responseEntity.getBody().getTipe().equals(Type.SUCCESS))
			throw new ServiceException(responseEntity.getBody().getMessage());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public UnitKerja getUnitKerja(String kode, HttpHeaders headers) throws ServiceException {
		ResponseEntity<EntityRestMessage> responseEntity = restTemplate.exchange("{host}/ehrm/satker/kode/{kode}", HttpMethod.GET, new HttpEntity<UnitKerja>(null, headers), EntityRestMessage.class, host, kode);
		
		if (!responseEntity.getBody().getTipe().equals(Type.ENTITY))
			throw new ServiceException(responseEntity.getBody().getMessage());
		
		Map<String, Object> maps = (Map<String, Object>) responseEntity.getBody().getModel();
		
		return objectCreator.createUnitKerja(maps);
	}

	public String createBasicAuthentication(String username, String password) {
		String auth = "superuser:dkakunsi";
		byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));

		return "Basic " + new String( encodedAuth );
	}

	static class ObjectCreator {

		private static ObjectCreator instance;
		
		private ObjectCreator() {
			super();
		}
		
		public static ObjectCreator getInstance() {
			if (instance== null)
				instance = new ObjectCreator();
			return instance;
		}
		
		@SuppressWarnings("unchecked")
		private Token createToken(Map<String, Object> maps) {
			Token token = new Token();
			token.setToken((String)maps.get("token"));
			token.setStatus(StatusToken.valueOf((String)maps.get("status")));
			token.setTanggalStr((String)maps.get("tanggalStr"));
			token.setExpireStr((String)maps.get("expireStr"));
			token.setPegawai(createPegawai((Map<String, Object>)maps.get("pegawai")));

			return token;
		}
		
		private List<Operator> createOperators(List<Map<String, Object>> list) {
			List<Operator> operators = new ArrayList<>();
			for (Map<String, Object> map : list)
				operators.add(createOperator(map));
			
			return operators;
		}

		private Operator createOperator(Map<String, Object> maps) {
			Operator operator = new Operator();
			operator.setRole(Role.valueOf((String)maps.get("role")));
			operator.setUsername((String)maps.get("username"));
			operator.setKodeAplikasi((String)maps.get("kodeAplikasi"));
			
			return operator;
		}
		
		@SuppressWarnings("unchecked")
		private Pegawai createPegawai(Map<String, Object> maps) {
			Pegawai pegawai = new Pegawai();
			pegawai.setNip((String)maps.get("nip"));
			pegawai.setUnitKerja(createUnitKerja((Map<String, Object>)maps.get("unitKerja")));
			pegawai.setIdPenduduk((int)maps.get("idPenduduk"));
			pegawai.setNik((String)maps.get("nik"));
			pegawai.setNama((String)maps.get("nama"));
			pegawai.setTanggalLahirStr((String)maps.get("tanggalLahirStr"));
			pegawai.setTelepon((String)maps.get("telepon"));
			pegawai.setEmail((String)maps.get("email"));

			pegawai.setListOperator(createOperators((List<Map<String, Object>>)maps.get("listOperator")));
			
			return pegawai;
		}
		
		private UnitKerja createUnitKerja(Map<String, Object> maps) {
			UnitKerja unitKerja = new UnitKerja();
			unitKerja.setId((Integer)maps.get("id"));
			unitKerja.setNama((String)maps.get("nama"));
			unitKerja.setTipe(TipeUnitKerja.valueOf((String)maps.get("tipe")));
			unitKerja.setSingkatan((String)maps.get("singkatan"));
			
			return unitKerja;
		}
		
	}
}
