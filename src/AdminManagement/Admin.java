/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdminManagement;

/**
 *
 * @author ZYAD KHALED
 */
public class Admin extends User {

    public Admin() {}

    public Admin(int id, String name, String password, String email) {
        super(id, name, password, email, Role.Admin);
    }
}
