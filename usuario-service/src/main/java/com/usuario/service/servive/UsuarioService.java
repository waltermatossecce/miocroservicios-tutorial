package com.usuario.service.servive;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuario.service.FeignClients.CarroFeignClient;
import com.usuario.service.FeignClients.MotoFeignClients;
import com.usuario.service.entidades.Usuario;
import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;
import com.usuario.service.repositorio.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
    
	public List<Usuario>getAll(){
		return usuarioRepository.findAll();
	}
	public Usuario getUsuarioById(int id) {
		return usuarioRepository.findById(id).orElse(null);
	}
	public Usuario save(Usuario usuario) {
		Usuario nuevoUsuario=usuarioRepository.save(usuario);
		return nuevoUsuario;
	}

	@Autowired
	private MotoFeignClients motoFeignClients;
	
	public Moto saveMoto(int usuarioId,Moto moto) {
		moto.setUsuarioId(usuarioId);
		Moto nuevaMoto=motoFeignClients.save(moto);
		return nuevaMoto;
	}
	@Autowired
	private CarroFeignClient carroFeignClient;
	
	public Carro saveCarro(int usuarioId,Carro carro) {
	  carro.setUsuarioId(usuarioId);
	  Carro nuevaCarro=carroFeignClient.saveCarros(carro);
	  return nuevaCarro;
	}
	//Get vehiculos 
	public Map<String, Object>getUsuariosAndVeiculos(int usuarioId){
		Map<String, Object>resultado=new HashMap<>();
	   	Usuario usuario=usuarioRepository.findById(usuarioId).orElse(null);
	   	if (usuario==null) {
			resultado.put("Mensaje", "El usuario no existe");
			return resultado;
		}
	   	resultado.put("Usuario", usuario);
	    List<Carro>carros=carroFeignClient.getCarros(usuarioId);
		if (carros.isEmpty()) {
			resultado.put("Mensaje", "Este uusario no tiene carros");   
		}else {
			resultado.put("Carros", carros);
		}
		List<Moto>motos=motoFeignClients.getMotos(usuarioId);
		if (motos.isEmpty()) {
			resultado.put("Mensaje", "Este usuario no tiene motos");
		}else {
			resultado.put("motos", motos);
		}
	    return resultado;
	   	
	}
	
	
	
}
