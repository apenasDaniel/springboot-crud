package crud.webapp.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import crud.webapp.springboot.controller.service.EmployeeService;
import crud.webapp.springboot.model.Employee;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	// Mostrar lista de funcionários
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listEmployees", employeeService.getAllEmployees());
		return "index";
		
	}
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		// criar model attribute para ligar aos dados 
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		// salvar funcionário no banco de dados
		employeeService.saveEmployee(employee);
		return "redirect:/";
	}	
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable (value = "id") long id, Model model) {
		
		// pegar funcionário do serviço
		Employee employee = employeeService.getEmployeeById(id);
		
		// setar funcionário como atributo model para pré-popular o form
		model.addAttribute("employee", employee);
		return "update_employee";
		
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value = "id") long id) {
		
		// chamar método para deletar funcionário		
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/";
	}
	
}