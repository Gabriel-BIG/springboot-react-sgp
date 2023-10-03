package br.uece.sgp.v1.apresentacao.controller;


import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.uece.sgp.v1.apresentacao.dto.ProdutoDto;
import br.uece.sgp.v1.apresentacao.dto.ProdutoDtoMapper;
import br.uece.sgp.v1.negocio.Produto;
import br.uece.sgp.v1.negocio.ServicoProduto;

@RestController
@RequestMapping("/v1/produtos")
public class ProdutoController {//(Antiga classe "InterfaceUsario.java"

	private final ServicoProduto produtoService;
	
	public ProdutoController(ServicoProduto produtoService) {
		this.produtoService = produtoService;
	}

	@GetMapping("/ping")
	public String ping() {
		return "...pong!";
	}
	
	@GetMapping//Read
	public List<ProdutoDto> listarTodos() {
		List<Produto> produtos = produtoService.listarProdutos();
		return ProdutoDtoMapper.toDtoList(produtos);
	}
	
	@GetMapping("/{id}")//Read
	public ProdutoDto obterPorId(@PathVariable Long id) {
		Produto produto = produtoService.buscarProdutoPorId(id);
		return ProdutoDtoMapper.toDto(produto);
	}

	@PostMapping//create
	public ProdutoDto criarProduto(@RequestBody ProdutoDto produtoDto) {
		Produto produto = ProdutoDtoMapper.fromDto(produtoDto);
		produto = produtoService.criarProduto(produto);
		return ProdutoDtoMapper.toDto(produto);
	}

	@DeleteMapping("/{id}")//Delete
	public void deletarProduto(@PathVariable Long id) {
		produtoService.removerProduto(new Produto(id));
	}
	
	@PutMapping("/{id}")//update
	public ProdutoDto editarProduto(@PathVariable Long id, @RequestBody ProdutoDto produtoDto) {
		produtoDto.setId(id);
		Produto produto = ProdutoDtoMapper.fromDto(produtoDto);
		Produto produtoAtualizado = produtoService.atualizarProduto(produto);
	    return ProdutoDtoMapper.toDto(produtoAtualizado);
	}

//    @PostMapping("/json/importar")
//    public void importarFuncionarios(@RequestBody List<FuncionarioDto> funcionariosImportados) {
//        funcionarioService.importarFuncionarios(FuncionarioDtoMapper.fromDtoList(funcionariosImportados));
//    }

//	@PostMapping("/csv/importar")
//	public void importarFuncionarios(@RequestParam("file") MultipartFile arquivo) throws IOException {
//		try (BufferedReader reader = new BufferedReader(new InputStreamReader(arquivo.getInputStream()))) {
//
//			List<FuncionarioDto> funcionariosDto = new ArrayList<>();
//			String linha;
//
//			for (int numLinha = 1; (linha = reader.readLine()) != null; numLinha++) {
//				// Divida a linha em campos usando a vírgula como delimitador
//
//				String[] campos = linha.split(";");
//				if (numLinha > 1 && campos.length == 3) { // Certifique-se de que há 3 campos: ID, Nome e Telefone
//					String id = campos[0].trim();
//					String nome = campos[1].trim();
//					String telefone = campos[2].trim();
//
//					// Crie um objeto FuncionarioDto com os dados lidos
//					FuncionarioDto funcionarioDto = new FuncionarioDto();
//					funcionarioDto.setId(Long.parseLong(id));
//					funcionarioDto.setNome(nome);
//					funcionarioDto.setTelefone(telefone);
//					funcionariosDto.add(funcionarioDto);
//
//				}
//			}
//
//			// Chame o serviço para importar o funcionario
//			funcionarioService.importarFuncionarios(funcionariosDto);
//
//		} catch (IOException e) {
//			throw e;
//		}
//	}
//
//	@GetMapping("/csv/exportar")
//	public void exportar(HttpServletResponse response) {
//		response.setContentType("text/csv");
//		response.setHeader("Content-Disposition", "attachment; filename=\"funcionarios.csv\"");
//
//		List<Funcionario> funcionarios = funcionarioService.listarTodos();
//
//		try (PrintWriter writer = response.getWriter()) {
//			// Escreve o cabeçalho do CSV
//			writer.println("ID;Nome;Telefone");
//
//			// Escreve os dados dos funcionarios no CSV
//			for (Funcionario funcionario : funcionarios) {
//				writer.println(funcionario.getId() + ";" + funcionario.getNome() + ";" + funcionario.getTelefone());
//			}
//		} catch (IOException e) {
//			// Trate exceções, se necessário
//		}
//	}
}
