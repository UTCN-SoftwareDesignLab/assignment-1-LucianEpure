package model;

import java.util.List;

public class Role {

	    private Long id;
	    private String roleTitle;
	    private List<Right> rights;

	    public Role(Long id, String role, List<Right> rights) {
	        this.id = id;
	        this.roleTitle = role;
	        this.rights = rights;
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getRoleTitle() {
	        return roleTitle;
	    }

	    public void setRoleTitle(String role) {
	        this.roleTitle = role;
	    }

	    public List<Right> getRights() {
	        return rights;
	    }

	    public void setRights(List<Right> rights) {
	        this.rights = rights;
	    }
}
