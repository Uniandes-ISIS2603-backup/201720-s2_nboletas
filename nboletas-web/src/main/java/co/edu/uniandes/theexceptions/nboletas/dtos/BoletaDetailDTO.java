/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.-
 */
package co.edu.uniandes.theexceptions.nboletas.dtos;

import co.edu.uniandes.theexceptions.nboletas.entities.BoletaEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.ComentarioEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.EnvioEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.FuncionEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.ReembolsoEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.SillaEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ISIS2603
 */
public class BoletaDetailDTO extends BoletaDTO {

    private ReembolsoDTO reembolso;

    private EnvioDTO envio;

    private UsuarioDTO usuario;

    private ComentarioDTO comentario;

    private FuncionDTO funcion;

    private SillaDTO silla;

    /**
     * Constructor por defecto
     */
    public BoletaDetailDTO() {
        super();
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public BoletaDetailDTO(BoletaEntity entity) {
        super(entity);
        if (entity != null) {
            ReembolsoEntity reembolsoA = entity.getReembolso();
            if (null != reembolsoA) {
                this.reembolso = new ReembolsoDTO(reembolsoA);
            }
            entity.setReembolso(null);

            EnvioEntity envioA = entity.getEnvio();
            if (null != envioA) {
                this.envio = new EnvioDTO(envioA);
            }
            entity.setEnvio(null);

            UsuarioEntity usuarioA = entity.getUsuario();
            if (null != usuarioA) {
                this.usuario = new UsuarioDTO(usuarioA);
            }
            entity.setUsuario(usuarioA);

            ComentarioEntity comentarioA = entity.getComentario();
            if (null != comentarioA) {
                this.comentario = new ComentarioDTO(comentarioA);
            }
            entity.setComentario(comentarioA);

            FuncionEntity funcionA = entity.getFuncion();
            if (null != funcionA) {
                this.funcion = new FuncionDTO(funcionA);
            }
            entity.setFuncion(funcionA);

            SillaEntity sillaA = entity.getSilla();
            if (null != sillaA) {
                this.silla = new SillaDTO(sillaA);
            }
            entity.setSilla(sillaA);
        }
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return
     */
    @Override
    public BoletaEntity toEntity() {
        BoletaEntity boletaE = super.toEntity();
        if (this.reembolso != null) {
            boletaE.setReembolso(reembolso.toEntity());
        }

        if (this.envio != null) {
            boletaE.setEnvio(envio.toEntity());
        }

        if (this.usuario != null) {
            boletaE.setUsuario(usuario.toEntity());
        }

        if (this.comentario != null) {
            boletaE.setComentario(comentario.toEntity());
        }

        if (this.funcion != null) {
            boletaE.setFuncion(funcion.toEntity());
        }

        if (this.silla != null) {
            boletaE.setSilla(silla.toEntity());
        }

        return boletaE;
    }

    public ReembolsoDTO getReembolso() {
        return reembolso;
    }

    public void setReembolso(ReembolsoDTO reembolso) {
        this.reembolso = reembolso;
    }

    public EnvioDTO getEnvio() {
        return envio;
    }

    public void setEnvio(EnvioDTO envio) {
        this.envio = envio;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public ComentarioDTO getComentario() {
        return comentario;
    }

    public void setComentario(ComentarioDTO comentario) {
        this.comentario = comentario;
    }

    public FuncionDTO getFuncion() {
        return funcion;
    }

    public void setFuncion(FuncionDTO funcion) {
        this.funcion = funcion;
    }

    public SillaDTO getSilla() {
        return silla;
    }

    public void setSilla(SillaDTO silla) {
        this.silla = silla;
    }

    public static List<BoletaDetailDTO> listBoletaEntity2BoletaDetailDTO(List<BoletaEntity> entityList) {
        List<BoletaDetailDTO> list = new ArrayList<>();
        for (BoletaEntity entity : entityList) {
            list.add(new BoletaDetailDTO(entity));
        }
        return list;
    }

    public static List<BoletaEntity> listBoletaDetailDTO2BoletaEntity(List<BoletaDetailDTO> detailDtoList) {
        List<BoletaEntity> list = new ArrayList<>();
        for (BoletaDetailDTO detail : detailDtoList) {
            list.add(detail.toEntity());
        }
        return list;
    }
}
