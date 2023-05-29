package fastbuilder.app.model;

import fastbuilder.app.model.subclass.Role;
import fastbuilder.app.model.subclass.Skill;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
public class PartyMemberModel {
    public String charClass;
    public List<Role> roles;
    public List<Skill> skills;
    
    public PartyMemberModel(String charClass, ArrayList<?> list) {
        this.charClass = charClass;
        if (list.get(0) instanceof Role) {
            this.roles = (ArrayList<Role>) list;
        } else if (list.get(0) instanceof Skill) {
            this.skills = (ArrayList<Skill>) list;
        }
    }
}
