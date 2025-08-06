
package itin_pc.seguridad;

import itin_pc.dao.UsuarioDAO;
import itin_pc.model.Usuario;
import itin_pc.util.Excepciones;

public class Autorizacion {
    
    
    
    public static boolean esAdmin(int usuario_id) throws Excepciones {
        UsuarioDAO usuario = new UsuarioDAO();
        Usuario u = usuario.obtenerUsuarioPorId(usuario_id);
        
        return u.getRol().equals("ADMIN");
    }
}
