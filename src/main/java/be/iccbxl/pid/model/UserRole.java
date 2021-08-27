package be.iccbxl.pid.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

	private int user_id;
	private int role_id;

    public UserRole(){

    }

    public UserRole(int idUser, int idRole){
        this.role_id = idRole;
        this.user_id = idUser;
    }

    public int getRole_id() {
        return role_id;
    }
    public int getUser_id() {
        return user_id;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}
