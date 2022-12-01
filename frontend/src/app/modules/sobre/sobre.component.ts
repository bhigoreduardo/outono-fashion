import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sobre',
  templateUrl: './sobre.component.html',
  styleUrls: ['./sobre.component.scss']
})
export class SobreComponent implements OnInit {

  boxDescription: any[] = [];
  backgroundAbout: string = '/assets/images/background-about.webp';

  constructor() { }

  ngOnInit(): void {
    this.initializeVars();
  }

  initializeVars(): void {
    this.boxDescription = [{
      iconClass: 'fas fa-cart-plus',
      title: 'Imaginou? A gente tem!',
      description: 'Não importa seu estilo, aqui você encontra qualquer look para atender sua inspiração ou necessidade, com toda a facilidade e conveniência da compra online. Atualmente, são mais de 6 mil marcas e 400 mil produtos em nosso portfólio! Acreditamos que moda inteligente é moda para todos - do jovem urbano à mãe contemporânea.'
    },
    {
      iconClass: 'fas fa-globe-americas',
      title: 'Revolucionamos o ecossistema da moda!',
      description: 'Inteligência está em nossa essência, na paixão por aprender sempre. Somos mais que uma empresa de moda, respiramos tecnologia para constantemente reinventar o mercado. Nosso propósito está em promover uma revolução no ecossistema da moda, com inteligência em todas as nossas relações: com o mercado, parceiros, colaboradores e consumidores.'
    },
    {
      iconClass: 'fas fa-tshirt',
      title: 'Oferecemos moda com curadoria!',
      description: 'Na dúvida de como combinar aquele sapato ou qual casaco escolher? Por meio dos nossos canais de informação (Dafiti Mag online, Blog Kanui e Blog Tricae) e de nosso super time de influencers (My Look) desenvolvemos conteúdos diários de moda com informações práticas de estilo, dicas de tamanhos, tendências e seleções com os melhores produtos.'
    },
    {
      iconClass: 'fas fa-shipping-fast',
      title: 'Entregamos a qualquer hora, em qualquer lugar!',
      description: 'Acesse os seus produtos favoritos e efetue a compra quando quiser: 7 dias por semana, 24 horas por dia, de qualquer lugar, com os aplicativos disponíveis para iOS e Android. Para te ajudar, oferecemos entrega grátis* para compras acima de R$ 99, conforme disponibilidade da região.'
    },
    {
      iconClass: 'fas fa-exchange-alt',
      title: 'Trocamos e devolvemos sem complicações!',
      description: 'Não gostou do produto ou não serviu? Sem preocupação! Você pode devolver qualquer item não usado, sem nenhum custo adicional, em prazo determinado conforme política de cada um dos nossos e-commerces. Em um minuto, você pode solicitar trocas e devoluções, direto nos sites, pelo tablet ou smartphone.'
    }];
  }

}
