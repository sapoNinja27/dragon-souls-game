//package Entidades;
//
//import Entidades.Players.Player;
//import enums.AnimacaoPlayer;
//import enums.DirecaoPlayer;
//
//import java.util.HashMap;
//
//public class GerenciadorAnimacoes {
//    //Atributos de executando ação
//    public boolean caindo, subindo,pousando, andando, parando, atacando, respirando, respirandoEmCombate, investindo, parandoInvestida;
//
//    public int framesMoved = 0, indexMoved = 4, maxIndexMoved = 12;
//    public int framesParan = 0, maxFramesParan = 15;
//    public int framesParado = 0, maxFramesParado = 17, indexParado = 0, maxIndexParado = 4;
//    public int framesPulo = 0, maxFramesPulo = 15, indexPul = 13, maxIndexPul = 15;
//    public int framesCai = 0, maxFramesCai = 15, indexCai = 16, maxIndexCai = 17;
//    public int framesCai2 = 0, maxFramesCai2 = 15;
//    public int framesAtk = 0;
//    public int maxFramesAtk = 5;
//    public int framesFur = 0;
//    public int indexAtk = 27;
//    public int maxIndexAtk = 33;
//    public int framesDash = 0, maxFramesDash = 11, indexDash = 19, maxIndexDash = 20;
//	public int framesDashS = 0, maxFramesDashS2 = 15, maxFramesDashS = 4, indexDashS = 20, maxIndexDashS = 23;
//
//    public void animacoesPlayer(Player player){
//        andar(player);
//    }
//    private void andar(Player player){
//        framesMoved++;
//        if (framesMoved >= 6) {
//            framesMoved = 0;
//            indexMoved++;
//            if (indexMoved == maxIndexMoved) {
//                indexMoved = 4;
//            }
//
//        }
//    }
//    public void movedX(Player player) {
//        if (parado) {
//            framesParado++;
//            if (framesParado == maxFramesParado) {
//                framesParado = 0;
//                indexParado++;
//                if (indexParado == maxIndexParado) {
//                    indexParado = 0;
//                }
//            }
//        }
//    }
//    public void atualizarIndex(){
//        //		if (combat) {
////			index = indexParado + 24;
////			frames++;
////			if (frames >= 200) {
////				frames = 0;
////				combat = false;
////			}
////		} else {
////			index = indexParado;
////		}
////		if (!isFreeX && moved && !dash && !dashS) {
////			index = indexMoved;
////		} else if (caiu_no_chao) {
//////			index = index;
////		} else if (dash) {
////			index = indexDash;
////		} else if (dashS) {
////			index = indexDashS;
////		} else if (dashS2) {
////			index = indexDashS;
////		} else if (subindo) {
////			index = indexPul;
////		} else if (caindo) {
////			index = indexCai;
////		} else if (!isFreeX && moved && !dash && !dashS) {
////			index = indexMoved;
////		} else if (atacando) {
////			index = indexAtk;
////		} else if (parado) {
////
////		}
//    }
////    public void movedY() {
////        if (cima && isFreeY) {
////            framesPulo++;
////            if (framesPulo == maxFramesPulo) {
////                framesPulo = 0;
////                if (indexPul != maxIndexPul) {
////                    indexPul++;
////                }
////                if (indexPul == maxIndexPul) {
////
////                }
////            }
////            setY(getY() - 4);
////        }
////
//////		if (caindo) {
//////			framesCai++;
//////			if (framesCai == maxFramesCai) {
//////				framesCai = 0;
//////				if (indexCai != maxIndexCai) {
//////					indexCai++;
//////				}
//////				if (indexCai == maxIndexCai) {
//////					caindo = false;
//////				}
//////			}
//////			setY(getY() + 4);
//////		}
//////		if (caiu_no_chao) {
//////			indexCai = 16;
//////			indexPul = 13;
//////			saiu_do_chao = false;
//////			index = 18;
//////			framesCai2++;
//////			if (framesCai2 == maxFramesCai2) {
//////				framesCai2 = 0;
//////				parado = true;
//////				caiu_no_chao = false;
//////			}
//////		}
////
////    }
////    public void movedBot() {
////        if (direita && moved) {
////            direcaoPlayer = DirecaoPlayer.DIREITA;
////            correr(xDouble() + speed);
////        }
////        if (esquerda && moved) {
////            direcaoPlayer = DirecaoPlayer.ESQUERDA;
////            correr(xDouble() - speed);
////        }
////        if (parado) {
////            framesParado++;
////            if (framesParado == maxFramesParado) {
////                framesParado = 0;
////                indexParado++;
////                if (indexParado == maxIndexParado) {
////                    indexParado = 0;
////                }
////            }
////        }
////        if (moved) {
////            framesMoved++;
////            if (framesMoved >= 6) {
////                framesMoved = 0;
////                indexMoved++;
////                if (indexMoved == maxIndexMoved) {
////                    indexMoved = 4;
////                }
////
////            }
////        }
////    }	public void nBot() {
////        movedX();
////        depth = 6;
////        speed = 6;
////		if (atacando) {
////			framesAtk++;
////			if (framesAtk == maxFramesAtk) {
////				framesAtk = 0;
////				indexAtk++;
////				if (indexAtk == maxIndexAtk) {
////					indexAtk = 26;
////					atacando = false;
////					parado = true;
////					combat = true;
////				}
////			}
////		}
////    }
//}
