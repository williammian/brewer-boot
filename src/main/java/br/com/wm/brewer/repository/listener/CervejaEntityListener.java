package br.com.wm.brewer.repository.listener;

import javax.persistence.PostLoad;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.wm.brewer.model.Cerveja;
import br.com.wm.brewer.storage.FotoStorage;

public class CervejaEntityListener {

	@Autowired
	//private FotoStorage fotoStorage;
	
	@PostLoad
	public void postLoad(final Cerveja cerveja) {
		//SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this); //faz as injeções com base no contexto corrente
		//cerveja.setUrlFoto(fotoStorage.getUrl(cerveja.getFotoOuMock()));
		//cerveja.setUrlThumbnailFoto(fotoStorage.getUrl(FotoStorage.THUMBNAIL_PREFIX + cerveja.getFotoOuMock()));
		
		cerveja.setUrlFoto(FotoStorage.URL + cerveja.getFotoOuMock());
		cerveja.setUrlThumbnailFoto(FotoStorage.URL + FotoStorage.THUMBNAIL_PREFIX + cerveja.getFotoOuMock());
	}
	
}
