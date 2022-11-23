import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { IEnderecoInput, IEnderecoModel } from 'src/app/model/IEndereco';
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
  array: string[] = ['1', '2', '3', '4'];

  activeEnderecoApelido(enderecoApelido: string) {
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

    this.contaService.activeEndereco(enderecoInput);
  }

  // User Vars
  usuarioModel!: IUsuarioModel;

  // Header User
  radioBoxes!: any[];
  valueSelected!: string;

  setValue(value: string) {
    this.valueSelected = value;
  }

  isValueSelected(value: string) {
    return this.valueSelected == value;
  }

  // Forms Vars
  formUsuarioModel!: FormGroup;
  formSetPassword!: FormGroup;
  formInsertTelefone!: FormGroup;
  formMessage!: FormGroup;
  formInsertAddress!: FormGroup;

  message: string | undefined;

  // Password Vars
  passwordCurrentBoolean: boolean = true;
  passwordCurrentType: string = 'password';

  passwordNewBoolean: boolean = true;
  passwordNewType: string = 'password';

  setPassword!: Boolean;

  // Telefone Vars
  telefonesModel: ITelefoneModel[] = [];

  setTelefone!: Boolean;
  newTelefone!: Boolean;

  // Slider Order
  orderSlider!: SwiperOptions;

  // Address Vars
  addressApiRes!: any;
  addressAdd!: Boolean;
  enderecosModel: IEnderecoModel[] = [];

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

  constructor(
    private formBuilder: FormBuilder,
    private contaService: ContaService
  ) { }

  ngOnInit(): void {
    this.getUsuarioLoginModel();
    this.initializeVars();
    this.initializeSlides();

    this.findEnderecosByUsuarioAsync();
  }

  getUsuarioLoginModel(): void {
    this.usuarioModel = JSON.parse(localStorage.getItem('usuarioModel')!);
    this.createForm(this.usuarioModel);
  }

  initializeVars(): void {
    this.valueSelected = 'dados-pessoais';
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

  createForm(usuarioModel: IUsuarioModel): void {
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

    this.formSetPassword = this.formBuilder.group({
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

    this.formInsertAddress = this.formBuilder.group({
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

  updateFormInsertAddress(addressApiRes: any) {
    this.formInsertAddress.patchValue({ enderecoBairro: addressApiRes.bairro, enderecoLogradouro: addressApiRes.logradouro, enderecoUf: addressApiRes.uf, enderecoCidade: addressApiRes.localidade });
    // this.formInsertAddress.setValue({enderecoBairro: addressApiRes.bairro, enderecoLogradouro: addressApiRes.logradouro});
  }

  sendUsuarioLogin(values: any): void {
    const usuarioInput: IUsuarioInput = {
      nome: values.nome,
      sobrenome: values.sobrenome,
      email: this.usuarioModel.email,
      cpfCnpj: this.usuarioModel.cpfCnpj,
      rgIe: this.usuarioModel.rgIe,
      dataNascimento: new Date(),
      genero: {
        id: this.usuarioModel.genero.id
      },
      newsletter: values.newsletter
    }

    this.contaService.update(usuarioInput, this.usuarioModel.id).subscribe(
      res => {
        localStorage.setItem('usuarioModel', JSON.stringify(res));
        this.message = "Alterações salvas com sucesso!";
      }, error => {
        this.message = error.error.userMessage;
      }
    )
  }

  setPasswordCurrent(): void {
    this.passwordCurrentBoolean = !this.passwordCurrentBoolean;
    this.passwordCurrentType = (this.passwordCurrentBoolean == true) ? 'password' : 'text';
  }

  setPasswordNew(): void {
    this.passwordNewBoolean = !this.passwordNewBoolean;
    this.passwordNewType = (this.passwordNewBoolean == true) ? 'password' : 'text';
  }

  sendSetPassword(values: any): void {
    const senhaInput: ISenhaInput = {
      senhaAtual: values.senhaAtual,
      novaSenha: values.novaSenha
    }

    this.contaService.setSenha(senhaInput, this.usuarioModel.id).subscribe(
      res => {
        this.formSetPassword.reset();
        this.setPassword = false;

        this.setPasswordCurrent();
        this.setPasswordNew();

        this.message = "Senha alterada com sucesso!";
      }, error => {
        this.message = error.error.userMessage;
      }
    )
  }

  sendInsertTelefone(values: any): void {
    const telefoneInput: ITelefoneInput = {
      numero: values.numero,
      usuario: {
        id: this.usuarioModel.id
      }
    }

    this.contaService.insertTelefone(telefoneInput).subscribe(
      res => {
        this.formInsertTelefone.reset();
        this.newTelefone = false;

        this.message = "Telefone salvo com sucesso!";
      }, error => {
        this.message = error.error.userMessage;
      }
    )
  }

  getTelefones(usuarioId: number) {
    return new Promise(resolve => {
      this.contaService.findTelefoneByUsuario(usuarioId).subscribe(
        data => {
          resolve(data);
          console.log(data)
        }
      )
    })
  }

  async findTelefones() {
    this.telefonesModel = <ITelefoneModel[]>await this.getTelefones(this.usuarioModel.id);
  }

  deleteTelefoneByNumero(numero: string) {

    this.contaService.deleteTelefoneByNumero(numero).subscribe(
      data => {
        this.getTelefones(this.usuarioModel.id);
        this.message = 'Número ' + numero + ' excluído com sucesso!';
        window.location.reload();
      },
      error => {
        this.message = error.userMessage;
      }
    );

  }

  initializeSlides() {
    this.orderSlider = {
      direction: 'horizontal',
      slidesPerView: 1,
      spaceBetween: 0,
      scrollbar: { draggable: true }
    }
  }

  searchCep(values: any) {
    this.contaService.searchCep(values.enderecoCep).subscribe(
      res => {
        this.addressApiRes = res;
        this.updateFormInsertAddress(this.addressApiRes);
      }, error => {
        this.message = error.error.message;
      }
    )
  }

  // POST Methods
  insertAddress(values: any) {
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

    this.contaService.insertAddress(enderecoInput).subscribe(
      res => {
        this.message = 'Endereço cadastrado com sucesso!';
      }, error => {
        this.message = error.userMessage;
      }
    );
  }

}