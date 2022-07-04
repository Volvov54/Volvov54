export interface CompWPS {
  name: string
  login: string
  ip: string
  ping: boolean
  p139: boolean
  p2000: boolean
  p3389: boolean
  p4899: boolean
}

export interface CompsWPS {
  data: CompWPS[]
}
