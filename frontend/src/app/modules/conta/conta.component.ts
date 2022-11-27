import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { IEnderecoInput, IEnderecoModel } from 'src/app/model/IEndereco';
import { IPedidoModel } from 'src/app/model/IPedido';
import { ISenhaInput } from 'src/app/model/ISenha';
import { ITelefoneInput, ITelefoneModel } from 'src/app/model/ITelefone';
import { IUsuarioInput, IUsuarioModel } from 'src/app/model/IUsuario';
import { SwiperOptions } from 'swiper';
import { ContaService } from './service/conta.service';

@Component({
  selector: 'app-conta',
  templateUrl: './conta.component.html',
  styleUrls: ['./conta.component.scss']
})
export class ContaComponent implements OnInit {

  // Usuário Vars
  usuarioModel!: IUsuarioModel;
  telefonesModel: ITelefoneModel[] = [];
  setTelefone!: Boolean;
  newTelefone!: Boolean;
  addressApiRes!: any;
  addressAdd!: Boolean;
  enderecosModel: IEnderecoModel[] = [];

  // Pedido Vars
  pedidosModel: IPedidoModel[] = [];
  orderSlider!: SwiperOptions;

  // Forms Vars
  formUsuarioModel!: FormGroup;
  formSetSenha!: FormGroup;
  formInsertTelefone!: FormGroup;
  formMessage!: FormGroup;
  formInsertEndereco!: FormGroup;

  // Password Vars
  passwordCurrentBoolean: boolean = true;
  passwordCurrentType: string = 'password';

  passwordNewBoolean: boolean = true;
  passwordNewType: string = 'password';

  setPassword!: Boolean;

  // Header
  radioBoxes!: any[];
  valueRadioBoxSelected!: string;

  message: string | undefined;

  constructor(
    private formBuilder: FormBuilder,
    private contaService: ContaService
  ) { }

  ngOnInit(): void {
    this.initializeVars();
    this.createForms();
    this.getUsuarioModel();
    this.findPedidosByUsuarioAsync();
    this.initializeSlides();
    this.findEnderecosByUsuarioAsync();
  }

  initializeVars(): void {
    this.valueRadioBoxSelected = 'dados-pessoais';
    this.setPassword = false;
    this.setTelefone = false;
    this.newTelefone = false
    this.addressAdd = false;

    this.radioBoxes = [
      {
        title: 'Dados pessoais',
        name: 'header-user',
        value: 'dados-pessoais',
        icon: 'fas fa-user-alt',
        placeholder: 'Dados pessoais'
      },
      {
        title: 'Meus pedidos',
        name: 'header-user',
        value: 'meus-pedidos',
        icon: 'fas fa-box',
        placeholder: 'Meus pedidos'
      },
      {
        title: 'Endereços',
        name: 'header-user',
        value: 'enderecos',
        icon: 'fas fa-map-marker-alt',
        placeholder: 'Endereços'
      },
      {
        title: 'Atendimentos',
        name: 'header-user',
        value: 'atendimentos',
        icon: 'fas fa-headset',
        placeholder: 'Atendimentos'
      }
    ]
  }

  createFormUsuarioModel(usuarioModel: IUsuarioModel): void {
    this.formUsuarioModel = this.formBuilder.group({
      nome: new FormControl(usuarioModel.nome, [Validators.required, Validators.minLength(5), Validators.maxLength(60)]),
      sobrenome: new FormControl(usuarioModel.sobrenome, [Validators.required, Validators.minLength(5), Validators.maxLength(60)]),
      email: new FormControl({ value: usuarioModel.email, disabled: true }, Validators.required),
      cpfCnpj: new FormControl({ value: usuarioModel.cpfCnpj, disabled: true }, Validators.required),
      rgIe: new FormControl({ value: usuarioModel.rgIe, disabled: true }, Validators.required),
      dataNascimento: new FormControl({ value: usuarioModel.dataNascimento, disabled: true }, Validators.required),
      genero: new FormControl({ value: usuarioModel.genero.descricao, disabled: true }, Validators.required),
      newsletter: new FormControl(usuarioModel.newsletter)
    });
  }

  createForms(): void {
    this.formSetSenha = this.formBuilder.group({
      senhaAtual: new FormControl(null, [Validators.required, Validators.minLength(5)]),
      novaSenha: new FormControl(null, [Validators.required, Validators.minLength(5)]),
    })

    this.formInsertTelefone = this.formBuilder.group({
      numero: new FormControl(null, [Validators.required, Validators.maxLength(11)])
    });

    this.formMessage = this.formBuilder.group({
      tipoSolicitacao: new FormControl(null, [Validators.required, Validators.maxLength(60)]),
      numeroPedido: new FormControl(null, Validators.required),
      assunto: new FormControl(null, [Validators.required, Validators.maxLength(60)]),
      mensagem: new FormControl(null, [Validators.required, Validators.maxLength(500)])
    });

    this.formInsertEndereco = this.formBuilder.group({
      enderecoCep: new FormControl(null, [Validators.required, Validators.maxLength(8)]),
      enderecoBairro: new FormControl(),
      enderecoLogradouro: new FormControl(null, [Validators.required, Validators.maxLength(60)]),
      enderecoNumero: new FormControl(null, [Validators.required, Validators.maxLength(6)]),
      enderecoComplemento: new FormControl(null, Validators.maxLength(60)),
      enderecoReferencia: new FormControl(null, [Validators.maxLength(60)]),
      enderecoUf: new FormControl(null, [Validators.required, Validators.maxLength(2)]),
      enderecoCidade: new FormControl(null, [Validators.required, Validators.maxLength(120)]),
      enderecoApelido: new FormControl(null, [Validators.required, Validators.maxLength(60)]),
      enderecoAtivo: new FormControl(null)
    });
  }

  getUsuarioModel(): void {
    this.usuarioModel = JSON.parse(localStorage.getItem('usuarioModel')!);
    this.createFormUsuarioModel(this.usuarioModel);
  }

  setValue(value: string) {
    this.valueRadioBoxSelected = value;
  }

  isValueRadioBoxSelected(value: string) {
    return this.valueRadioBoxSelected == value;
  }

  findPedidosByUsuario(usuarioId: number) {
    return new Promise(resolve => {
      this.contaService.findPedidosByUsuario(usuarioId).subscribe(
        res => {
          resolve(res);
        }
      )
    });
  }

  async findPedidosByUsuarioAsync() {
    this.pedidosModel = <IPedidoModel[]>await this.findPedidosByUsuario(this.usuarioModel.id);
  }

  initializeSlides() {
    this.orderSlider = {
      direction: 'horizontal',
      slidesPerView: 1,
      spaceBetween: 0,
      scrollbar: { draggable: true }
    }
  }

  findEnderecosByUsuario(usuarioId: number) {
    return new Promise(resolve => {
      this.contaService.findEnderecosByUsuario(usuarioId).subscribe(
        res => {
          resolve(res);
        }
      );
    });
  }

  async findEnderecosByUsuarioAsync() {
    this.enderecosModel = <IEnderecoModel[]>await this.findEnderecosByUsuario(this.usuarioModel.id);
  }

  update(usuarioInput: IUsuarioInput) {
    return new Promise(resolve => {
      this.contaService.update(usuarioInput, this.usuarioModel.id).subscribe(
        res => {
          localStorage.setItem('usuarioModel', JSON.stringify(res));
          this.getUsuarioModel();
          this.message = "Alterações salvas com sucesso!";
        }, error => {
          this.message = error.error.userMessage;
        }
      )
    });
  }

  async updateAsync(values: any) {
    const usuarioInput: IUsuarioInput = {
      nome: values.nome,
      sobrenome: values.sobrenome,
      email: this.usuarioModel.email,
      cpfCnpj: this.usuarioModel.cpfCnpj,
      rgIe: this.usuarioModel.rgIe,
      dataNascimento: this.usuarioModel.dataNascimento,
      genero: {
        id: this.usuarioModel.genero.id
      },
      newsletter: values.newsletter
    }

    await this.update(usuarioInput);
  }

  setPasswordCurrent(): void {
    this.passwordCurrentBoolean = !this.passwordCurrentBoolean;
    this.passwordCurrentType = (this.passwordCurrentBoolean == true) ? 'password' : 'text';
  }

  setPasswordNew(): void {
    this.passwordNewBoolean = !this.passwordNewBoolean;
    this.passwordNewType = (this.passwordNewBoolean == true) ? 'password' : 'text';
  }

  setSenha(senhaInput: ISenhaInput) {
    return new Promise(resolve => {
      this.contaService.setSenha(senhaInput, this.usuarioModel.id).subscribe(
        res => {
          this.formSetSenha.reset();
          this.setPassword = false;

          this.setPasswordCurrent();
          this.setPasswordNew();

          this.message = "Senha alterada com sucesso!";
        }, error => {
          this.message = error.error.userMessage;
        }
      )
    });
  }

  async setSenhaAsync(values: any) {
    const senhaInput: ISenhaInput = {
      senhaAtual: values.senhaAtual,
      novaSenha: values.novaSenha
    }

    await this.setSenha(senhaInput);
  }

  findTelefonesByUsuario(usuarioId: number) {
    return new Promise(resolve => {
      this.contaService.findTelefonesByUsuario(usuarioId).subscribe(
        data => {
          resolve(data);
          console.log(data)
        }
      )
    })
  }

  async findTelefonesByUsuarioAsync() {
    this.telefonesModel = <ITelefoneModel[]>await this.findTelefonesByUsuario(this.usuarioModel.id);
  }

  insertTelefone(telefoneInput: ITelefoneInput) {
    return new Promise(resolve => {
      this.contaService.insertTelefone(telefoneInput).subscribe(
        res => {
          this.findTelefonesByUsuarioAsync();
          this.formInsertTelefone.reset();
          this.newTelefone = false;

          this.message = "Telefone salvo com sucesso!";
        }, error => {
          this.message = error.error.userMessage;
        }
      )
    });
  }

  async insertTelefoneAsync(values: any) {
    const telefoneInput: ITelefoneInput = {
      numero: values.numero,
      usuario: {
        id: this.usuarioModel.id
      }
    }

    await this.insertTelefone(telefoneInput);
  }

  deleteTelefoneByNumero(numero: string) {
    return new Promise(resolve => {
      this.contaService.deleteTelefoneByNumero(numero).subscribe(
        data => {
          this.findTelefonesByUsuarioAsync();
          this.message = 'Número telefone excluído com sucesso!';
          // window.location.reload();
        },
        error => {
          this.message = error.userMessage;
        }
      );
    });
  }

  async deleteTelefoneByNumeroAsync(numero: string) {
    await this.deleteTelefoneByNumero(numero);
  }

  patchFormInsertEndereco(addressApiRes: any) {
    this.formInsertEndereco.patchValue({
      enderecoBairro: addressApiRes.bairro,
      enderecoLogradouro: addressApiRes.logradouro,
      enderecoUf: addressApiRes.uf,
      enderecoCidade: addressApiRes.localidade
    });
    // this.formInsertEndereco.setValue({enderecoBairro: addressApiRes.bairro, enderecoLogradouro: addressApiRes.logradouro});
  }

  searchCep(values: any) {
    return new Promise(resolve => {
      this.contaService.searchCep(values.enderecoCep).subscribe(
        res => {
          this.addressApiRes = res;
          this.patchFormInsertEndereco(this.addressApiRes);
        }, error => {
          this.message = error.error.message;
        }
      )
    });
  }

  async searchCepAsync(values: any) {
    await this.searchCep(values);
  }

  insertEndereco(enderecoInput: IEnderecoInput) {
    return new Promise(resolve => {
      this.contaService.insertEndereco(enderecoInput).subscribe(
        res => {
          this.findEnderecosByUsuarioAsync();
          this.formInsertEndereco.reset();
          this.addressAdd = false;

          this.message = 'Endereço cadastrado com sucesso!';
        }, error => {
          this.message = error.userMessage;
        }
      );
    });
  }

  async insertEnderecoAsync(values: any) {
    const enderecoInput: IEnderecoInput = {
      id: { enderecoApelido: values.enderecoApelido },
      usuario: { id: this.usuarioModel.id },
      enderecoCep: values.enderecoCep,
      enderecoBairro: values.enderecoBairro,
      enderecoLogradouro: values.enderecoLogradouro,
      enderecoNumero: values.enderecoNumero,
      enderecoComplemento: values.enderecoComplemento,
      enderecoReferencia: values.enderecoReferencia,
      enderecoUf: values.enderecoUf,
      enderecoCidade: values.enderecoCidade,
      enderecoAtivo: values.enderecoAtivo
    }

    await this.insertEndereco(enderecoInput);
  }

  activeEnderecoApelido(enderecoInput: IEnderecoInput) {
    return new Promise(resolve => {
      this.contaService.activeEndereco(enderecoInput).subscribe(
        res => {
          this.findEnderecosByUsuarioAsync();
        }, error => {
          this.message = error.userMessage;
        }
      );
    });
  }

  async activeEnderecoApelidoAsync(enderecoApelido: string) {
    const enderecoModel = this.enderecosModel.filter(endereco => endereco.id.enderecoApelido == enderecoApelido)[0];

    const enderecoInput: IEnderecoInput = {
      id: { enderecoApelido: enderecoModel.id.enderecoApelido },
      usuario: { id: this.usuarioModel.id },
      enderecoCep: enderecoModel.enderecoCep,
      enderecoBairro: enderecoModel.enderecoBairro,
      enderecoLogradouro: enderecoModel.enderecoLogradouro,
      enderecoNumero: enderecoModel.enderecoNumero,
      enderecoComplemento: enderecoModel.enderecoComplemento,
      enderecoReferencia: enderecoModel.enderecoReferencia,
      enderecoUf: enderecoModel.enderecoUf,
      enderecoCidade: enderecoModel.enderecoCidade,
      enderecoAtivo: enderecoModel.enderecoAtivo
    }

    await this.activeEnderecoApelido(enderecoInput);
  }

  clearMessage(value: any): void {
    this.message = undefined;
  }

}